import React, {PureComponent, Component} from "react";
import {connect} from "react-redux";

import SockJS from 'sockjs-client';
import { Stomp } from 'stompjs/lib/stomp';


import {banksRequest, banksUpdate} from "./BankActions";
import Bank from "./Bank";

@connect(
    state => ({
        banks: state.banks,
        auth: state.auth
    }), {
        banksRequest,
        banksUpdate
    }
)
export default class BankList extends Component {

    constructor(props) {
        super(props);

        this.props.banksRequest();
        this.printBanks = this.printBanks.bind(this);

        let stompClient;
        let dispatch = this;

        let url = '/api/super-bank-app?access_token=' + this.props.auth.access_token;

        const socket = new SockJS(url);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/topic/banks', (data) => {
                switch (data.headers.event) {
                    case 'update': {
                        dispatch.props.banksUpdate(JSON.parse(data.body));
                    }
                }
            });
        });
    }

    printBanks(banks) {
        return banks.banks.map(bank => {
            return <Bank bank={bank} key={bank.id}/>
        });
    }

    render() {

        const {banks} = this.props;
        return (
            <div id="banks-page">
                <h1>Banks</h1>
                {this.printBanks(banks)}
            </div>
        );
    }
}
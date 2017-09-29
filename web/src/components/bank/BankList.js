import React, {Component} from "react";
import {connect} from "react-redux";
import {Link} from "react-router";

import SockJS from "sockjs-client";
import {Stomp} from "stompjs/lib/stomp";

import {banksRequest, banksUpdate} from "../../actions/BankActions";

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

        let url = '/api/super-bank-app?access_token=' + this.props.auth.get('access_token');

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
        return banks.get('banks').map(bank => {
            return (
                <tr key={bank.get('id')}>
                    <td>{bank.get('id')}</td>
                    <td><Link to={'/account/banks/' + bank.get('id')}>{bank.get('name')}</Link></td>
                </tr>
            )
        });
    }

    render() {

        const {banks} = this.props;
        return (
            <div id="banks-page">
                <h1>Banks</h1>
                <table className="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                        {this.printBanks(banks)}
                    </tbody>
                </table>
                {this.props.children}
            </div>
        );
    }
}
import React, {PureComponent, Component} from "react";
import {connect} from "react-redux";

import SockJS from 'sockjs-client';
import { Stomp } from 'stompjs/lib/stomp';


import {creditsRequest, creditsUpdate} from "./CreditsActions";
import Credit from "./Credit";

@connect(
    state => ({
        credits: state.credits
    }), {
        creditsRequest,
        creditsUpdate
    }
)
export default class Login extends Component {

    constructor(props) {
        super(props);

        this.props.creditsRequest();
        this.printCredits = this.printCredits.bind(this);

        let stompClient;
        let dispatch = this;

        const socket = new SockJS('/super-bank-app');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/topic/credits', (data) => {
                switch (data.headers.event) {
                    case 'update': {
                        dispatch.props.creditsUpdate(JSON.parse(data.body));
                    }
                }
            });
        });
    }

    printCredits(credits) {
        return credits.credits.map(credit => {
            return <Credit credit={credit}/>
        });
    }

    render() {

        const {credits} = this.props;
        console.log(credits);

        return (
            <div id="credits-page">
                <h1>Credits</h1>
                {this.printCredits(credits)}
            </div>
        );
    }
}
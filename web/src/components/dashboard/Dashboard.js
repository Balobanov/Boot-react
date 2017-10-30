import React, {Component} from "react";
import {connect} from "react-redux";
import SockJS from "sockjs-client";
import {Stomp} from "stompjs/lib/stomp";

@connect(
    state => ({
        auth: state.auth
    }), {}
)
export default class Dashboard extends Component {

    constructor(props) {
        super(props);
        // let stompClient;
        // let dispatch = this;
        //
        // let url = '/api/super-bank-app?access_token=' + this.props.auth.access_token;
        //
        // const socket = new SockJS(url);
        // stompClient = Stomp.over(socket);
        // stompClient.connect({}, function (frame) {
        //     stompClient.subscribe('/topic/banks', (data) => {
        //         switch (data.headers.event) {
        //             case 'update': {
        //                 dispatch.props.banksUpdate(JSON.parse(data.body));
        //             }
        //         }
        //     });
        // });
    }

    render() {
        return (
            <div id="dashboard-page">
                <h1>Dashboard</h1>
                {this.props.children}
            </div>
        );
    }
}
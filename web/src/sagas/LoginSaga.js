import { take, fork, cancel, call, put, cancelled } from 'redux-saga/effects'
import { browserHistory, hashHistory } from "react-router";

import {loginFailed, loginSuccess} from "../actions/LoginActions";
import {authToken} from "../actions/AuthActions";
import {creditsUpdate} from "../actions/BankActions";
import {LOGIN_REQUESTING} from "../constants/LoginConstants";
import handleApiErrors from '../helpers';

function loginApi (email, password) {
    return fetch(`/api//oauth/token?grant_type=password&username=${email}&password=${password}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Basic ${btoa('clientapp:123456')}`
        },
        // body: JSON.stringify({ email, password }),
    })
        .then(handleApiErrors)
        .then(response => response.json())
        .then(json => json)
        .catch((error) => { throw error })
}

// function* logout () {
//     yield put(unsetClient())
//
//     localStorage.removeItem('token')
//
//     hashHistory.push('/login')
// }

// let stompClient;
// function* connectToWebsocket() {
//     const socket = new SockJS('/super-bank-app');
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//         stompClient.subscribe('/topic/credits', function (data) {
//             switch (data.headers.event){
//                 case 'update': {
//                    yield put(creditsUpdate(JSON.parse(data.body)));
//                     return;
//                 }
//             }
//         });
//     });
// }

function* startLogin(email, password) {
    let auth;

    try {

        auth = yield call(loginApi, email, password);
        yield put(authToken(auth));

        yield put(loginSuccess());
        localStorage.setItem('auth', JSON.stringify(auth));

        hashHistory.push('/account');
    }
    catch (error) {
        yield put(loginFailed(error));
    }
    finally {
        // if (yield cancelled()) {
        //     hashHistory.push('/login')
        // }
    }

    return auth;
}

export default function* loginWatcher() {

    while (true) {

        const {email, password} = yield take(LOGIN_REQUESTING);

        const logginingTask = yield fork(startLogin, email, password);
        // const logginingTask = yield fork(startLogin, "admin", "12345");

        // const action = yield take([CLIENT_UNSET, LOGIN_ERROR])
        // if (action.type === CLIENT_UNSET) yield cancel(task)
        // yield call(logout)
    }
}
import { take, fork, cancel, call, put, cancelled } from 'redux-saga/effects'
import {LOGIN_ERROR, LOGIN_REQUESTING, LOGIN_SUCCESS} from "../constants/LoginConstants";
import { browserHistory } from "react-router";

function handleApiErrors (response) {
    if (!response.ok) throw Error(response.statusText)
    return response
}


function loginApi (email, password) {
    return fetch(`/oauth/token?grant_type=password&username=${email}&password=${password}`, {
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
//     browserHistory.push('/login')
// }

function* startLogin(email, password) {
    let auth;

    try {

        auth = yield call(loginApi, email, password);

        // yield put(setClient(token))

        yield put({ type: LOGIN_SUCCESS, auth });

        localStorage.setItem('token', JSON.stringify(auth));

        browserHistory.push('/');
    }
    catch (error) {
        yield put({ type: LOGIN_ERROR, error });
    }
    finally {
        // if (yield cancelled()) {
        //     browserHistory.push('/login')
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
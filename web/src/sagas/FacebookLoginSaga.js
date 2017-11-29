import {LOGIN_FACEBOOK_REQUESTING} from "../constants/FacebookConstants";
import handleApiErrors from '../helpers';
import {authToken} from "../actions/AuthActions";
import {facebookLoginSuccess, facebookLoginFaile} from "../actions/FacebookActions";

import { take, fork, cancel, call, put, cancelled, takeEvery } from 'redux-saga/effects'
import { browserHistory, hashHistory } from "react-router";

function fetchUser (access_token) {
    return fetch('/api/facebook', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ access_token }),
    })
        .then(handleApiErrors)
        .then(response => response.json())
        .then(json => json)
        .catch((error) => { throw error })
}

function* startLogin(action) {
    let auth;
    try {
        auth = yield call(fetchUser, action.token);
        yield put(authToken(auth));

        yield put(facebookLoginSuccess());
        localStorage.setItem('auth', JSON.stringify(auth));

        hashHistory.push('/account');
    }
    catch (error) {
        yield put(facebookLoginFaile(error));
    }
    finally {}

    return auth;
}

export default function* facebookWatcher() {
    yield [
        takeEvery(LOGIN_FACEBOOK_REQUESTING, startLogin)
    ];
}
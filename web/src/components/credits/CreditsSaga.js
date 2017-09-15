import { take, fork, cancel, call, put, cancelled, takeLatest, select } from 'redux-saga/effects'
import { browserHistory, hashHistory } from "react-router";
import {CREDITS_REQUEST} from "./CreditsConstants";
import handleApiErrors from '../../helpers';
import {creditsFailed, creditsSuccess} from "./CreditsActions";

const creditsAPI = (token) => {
    return fetch(`/credits`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        // body: JSON.stringify({ email, password }),
    })
        .then(handleApiErrors)
        .then(response => response.json())
        .then(json => json)
        .catch((error) => { throw error })
};

export const getAuth= state => state.auth;
function* requestCredits() {
    let credits;
    let auth;

    try {
        auth = yield select(getAuth);
        credits = yield call(creditsAPI, auth.access_token);
        yield put(creditsSuccess(credits));
    } catch (errors) {
        yield put(creditsFailed(errors));
    }
}

export default function* creditsWatcher() {
    yield [
        takeLatest(CREDITS_REQUEST, requestCredits)
    ];
}
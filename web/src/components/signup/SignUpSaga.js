import {call, put, takeLatest} from "redux-saga/effects";
import { browserHistory, hashHistory } from "react-router";

import handleApiErrors from "../../helpers";
import {SIGN_UP_FAILED, SIGN_UP_REQUESTING, SIGN_UP_SUCCESS} from "./SignUpConstants";

const callSignUpAPI = (email, password, code) => {
    return fetch(`/signup`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email, password, code}),
    })
        .then(handleApiErrors)
        .then(response => response.json())
        .then(json => json)
        .catch((error) => {
            throw error
        })
};

function* startSignUp(action) {

    try {
        const {email, password, code} = action;
        const response = yield call(callSignUpAPI, email, password, code);
        yield put({type: SIGN_UP_SUCCESS, response});
        hashHistory.push('/login');
    }
    catch (error) {
        yield put({type: SIGN_UP_FAILED, error});
    }
}


export default function* signUpWatcher() {
    yield takeLatest(SIGN_UP_REQUESTING, startSignUp);
};
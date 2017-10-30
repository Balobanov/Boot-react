import { take, fork, cancel, call, put, cancelled, takeLatest, select } from 'redux-saga/effects'
import { browserHistory, hashHistory } from "react-router";
import {BANKS_REQUEST} from "../constants/BankConstants";
import handleApiErrors from '../helpers';
import {banksFailed, banksSuccess} from "../actions/BankActions";

const banksAPI = (token) => {
    return fetch(`/api/banks`, {
        method: 'GET',
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
function* requestBanks() {
    let banks;
    let auth;

    try {
        auth = yield select(getAuth);
        banks = yield call(banksAPI, auth.get('access_token'));
        yield put(banksSuccess(banks));
    } catch (errors) {
        yield put(banksFailed(errors));
    }
}

export default function* banksWatcher() {
    yield [
        takeLatest(BANKS_REQUEST, requestBanks)
    ];
}
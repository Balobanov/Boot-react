import {call, put, takeEvery} from "redux-saga/effects";
import {hashHistory} from "react-router";
import {LOGOUT_REQUESTING} from "../constants/LogoutConstants";
import {authClear} from "../actions/AuthActions";

function* logoutProcess() {
    localStorage.removeItem('auth');

    yield put(authClear());

    hashHistory.push('/login');
}

export default function* logoutWatcher() {
    yield [
        takeEvery(LOGOUT_REQUESTING, logoutProcess)
    ];
}
import {call, put, takeEvery} from "redux-saga/effects";
import {hashHistory} from "react-router";
import {LOGOUT_REQUESTING} from "./LogoutConstants";
import {authClear} from "../auth/AuthActions";

function* logoutProcess() {
    localStorage.removeItem('auth');

    yield put(authClear());

    hashHistory.push('/');
}

export default function* logoutWatcher() {
    yield [
        takeEvery(LOGOUT_REQUESTING, logoutProcess)
    ];
}
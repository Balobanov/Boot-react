import { take, fork, cancel, call, put, cancelled, takeLatest } from 'redux-saga/effects'
import { browserHistory, hashHistory } from "react-router";
import {CREDITS_REQUEST} from "../constants/CreditsConstants";

export default function creditsWatcher() {
    yield [
        takeLatest(CREDITS_REQUEST)
    ];
}
import ReactDOM from "react-dom";
import React from "react";
import {Provider} from "react-redux";
import {browserHistory, hashHistory, Route, Router, IndexRoute} from "react-router";
import {applyMiddleware, createStore} from "redux";
import createSagaMiddleware from 'redux-saga';
import "babel-polyfill";

import IndexRedux from "./index-redux";
import IndexSagas from "./index-saga";

import MainContainer from "./components/MainContainer";
import WelcomePage from "./components/welcomepage/WelcomePage";
import SignUp from "./components/signup/SignUp";
import Login from "./components/login/Login";

import "./style/main.css";

const sagaMiddleware = createSagaMiddleware();

const composeSetup = process.env.NODE_ENV !== 'production' && typeof window === 'object' &&
window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ ?
    window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ : compose;

const store = createStore(
    IndexRedux,
    composeSetup(applyMiddleware(sagaMiddleware)), // allows redux devtools to watch sagas
);

// Begin our Index Saga
sagaMiddleware.run(IndexSagas);

ReactDOM.render(
    <Provider store={store}>
        <Router history={hashHistory}>
            <Route path="/" component={MainContainer} >
                <IndexRoute component={WelcomePage}/>
                <Route path="/signup" component={SignUp}/>
                <Route path="/login" component={Login}/>
            </Route>
        </Router>
    </Provider>
, document.getElementById('secret_bank_app'));
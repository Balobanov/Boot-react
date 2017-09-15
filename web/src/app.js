import ReactDOM from "react-dom";
import React from "react";
import {Provider} from "react-redux";
import {hashHistory, IndexRoute, Route, Router} from "react-router";
import {applyMiddleware, createStore} from "redux";
import createSagaMiddleware from "redux-saga";
import "babel-polyfill";

import IndexRedux from "./index-redux";
import IndexSagas from "./index-saga";

import MainContainer from "./components/MainContainer";
import WelcomePage from "./components/welcomepage/WelcomePage";
import SignUp from "./components/signup/SignUp";
import Login from "./components/login/Login";
import Credits from "./components/credits/CreditsList";

import {authToken} from "./components/auth/AuthActions";

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

const checkCreditAuthorization = ({dispatch, getState}) => {
    return (nextState, replace, next) => {

        if (getState().auth.access_token) {
            return next();
        }

        replace('/login');
        return next();
    }
};

export const isAuthenticated = ({dispatch, getState}) => {
    return (nextState, replace, next) => {
        let auth = JSON.parse(localStorage.getItem('auth'));

        if (auth && auth.access_token) {
            dispatch(authToken(auth));
        }

        return next();
    }
};

ReactDOM.render(
    <Provider store={store}>
        <Router history={hashHistory}>
            <Route path="/" component={MainContainer} onEnter={isAuthenticated(store)}>
                <IndexRoute component={WelcomePage} onEnter={isAuthenticated(store)}/>
                <Route path="/signup" component={SignUp}/>
                <Route path="/login" component={Login}/>
                <Route path="/credits" component={Credits} onEnter={checkCreditAuthorization(store)}/>
            </Route>
        </Router>
    </Provider>
    , document.getElementById('secret_bank_app'));
import ReactDOM from "react-dom";
import React from "react";
import {Provider} from "react-redux";
import {hashHistory, IndexRoute, Route, Router} from "react-router";
import {applyMiddleware, createStore} from "redux";
import createSagaMiddleware from "redux-saga";
import "babel-polyfill";

import IndexRedux from "./index-redux";
import IndexSagas from "./index-saga";

import MainContainer from "./containers/MainContainer";
import WelcomePage from "./components/welcomepage/WelcomePage";
import SignUp from "./components/signup/SignUp";
import Login from "./components/login/Login";
import Banks from "./components/bank/BankList";

import {authToken} from "./actions/AuthActions";

import "./style/main.css";
import 'bootstrap-social/bootstrap-social.css'
import BankEdit from "./components/bank/BankEdit";
import Dashboard from "./components/dashboard/Dashboard";

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

const checkAuthorization = ({dispatch, getState}) => {
    return (nextState, replace, next) => {

        if (getState().auth.get('access_token')) {
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
                <Route path="/account" component={Dashboard} onEnter={checkAuthorization(store)}>
                    <Route path="banks" component={Banks}>
                        <Route path=":id" component={BankEdit}/>
                    </Route>
                </Route>
            </Route>
        </Router>
    </Provider>
    , document.getElementById('secret_bank_app'));
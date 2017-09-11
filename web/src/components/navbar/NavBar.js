/**
 * Created by user on 11.09.17.
 */

import React, { PureComponent } from 'react';
import {Link} from "react-router";

export default class NavBar extends PureComponent {

    render() {
        return (
            <div id="navigation">
                <nav className="navbar navbar-inverse">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <a className="navbar-brand" href="#">ReactBoot</a>
                        </div>
                        <ul className="nav navbar-nav">
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/credits">Credits</Link></li>
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li><Link to="/signup" activeClassName="active"><span className="glyphicon glyphicon-user"/> Sign Up</Link></li>
                            <li><Link to="/login" activeClassName="active"><span className="glyphicon glyphicon-log-in"/> Login</Link></li>
                        </ul>
                    </div>
                </nav>
            </div>
        );
    }
}
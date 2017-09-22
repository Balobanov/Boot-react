import React, {PureComponent} from "react";
import {Link} from "react-router";
import {connect} from "react-redux";
import {logoutRequest} from "../logout/LogoutActions";

@connect(
    state => ({
        auth: state.auth
    }), {logoutRequest})
export default class NavBar extends PureComponent {

    constructor(props) {
        super(props);

        this.unauthorized = this.unauthorized.bind(this);
        this.authorized = this.authorized.bind(this);
        this.logout = this.logout.bind(this);
    }

    unauthorized() {
        return (
            <ul className="nav navbar-nav navbar-right">
                 <li><Link to="/signup" activeClassName="active"><span className="glyphicon glyphicon-user"/>Sign Up</Link></li>
                 <li><Link to="/login" activeClassName="active"><span className="glyphicon glyphicon-log-in"/>Login</Link></li>
            </ul>
        );
    }

    logout(){
       this.props.logoutRequest();
    }

    authorized() {
        return (
            <ul className="nav navbar-nav navbar-right">
                <li className="cursor"><Link activeClassName="active" onClick={this.logout}><span className="glyphicon glyphicon-log-in cursor"/>Logout</Link></li>
            </ul>
        );
    }


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
                            {
                                this.props.auth.access_token ? <li><Link to="/banks">Banks</Link></li> : null
                            }
                        </ul>
                        {
                            this.props.auth.access_token
                                ? this.authorized()
                                : this.unauthorized()
                        }
                    </div>
                </nav>
            </div>
        );
    }
}
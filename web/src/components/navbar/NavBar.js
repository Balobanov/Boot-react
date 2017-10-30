import React, {PureComponent} from "react";
import {Link} from "react-router";
import {connect} from "react-redux";
import {logoutRequest} from "../../actions/LogoutActions";

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
            <div className="nav navbar-nav navbar-right">
                <Link to="/signup" className="btn btn-default navbar-btn nav-btn-margin">Sign up</Link>
                <Link to="/login" className="btn btn-default navbar-btn nav-btn-margin">Login</Link>
            </div>
        );
    }

    logout() {
        this.props.logoutRequest();
    }

    authorized() {
        return (
            <div className="nav navbar-nav navbar-right">
                <button type="button" onClick={this.logout} className="btn btn-default navbar-btn nav-btn-margin">Logout</button>
            </div>
        );
    }


    render() {
        return (
            <div id="navigation">
                <nav className="navbar navbar-default navbar-inverse" role="navigation">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <Link to="/account" className="navbar-brand" href="#">Dashboard</Link>
                        </div>
                        <ul className="nav navbar-nav">
                            <li><Link to="/">Home</Link></li>
                            {
                                this.props.auth.get('access_token') ? <li><Link to="/account/banks">Banks</Link></li> : null
                            }
                        </ul>
                        {
                            this.props.auth.get('access_token')
                                ? this.authorized()
                                : this.unauthorized()
                        }
                    </div>
                </nav>
            </div>
        );
    }
}
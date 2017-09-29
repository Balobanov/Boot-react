import React, {PureComponent} from "react";
import {connect} from "react-redux";
import {reduxForm} from "redux-form";

import {loginRequest} from "../../actions/LoginActions";

const validateFormFields = ['email', 'password'];
const formName = 'login';

const validate = (values) => {
    // const { email } = values;
    // const errors = {};
    //
    // if (email && !/^[\w\-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) {
    //     errors.email = 'Invalid email format!';
    // }
    //
    // return errors;
    // console.log(values);
};

/**
 * TODO: validate fields with function. Use <Field/>
 */
@connect(
    state => ({
        login: state.login
    }), {
        loginRequest
    })
@reduxForm({
    form: formName,
    fields: validateFormFields,
})
export default class Login extends PureComponent {

    constructor(props) {
        super(props);

        this.submitLogin = this.submitLogin.bind(this);
    }

    submitLogin({email, password}) {
        this.props.loginRequest(email, password);
    }

    render() {
        const {handleSubmit, fields: {email, password}} = this.props;
        return (
            <div id="login-page">
                <h1>Login</h1>
                <section id="loginform" className="outer-wrapper">
                    <div className="inner-wrapper">
                        <div className="container">
                            <div className="row">
                                <div className="col-sm-4 col-sm-offset-4">
                                    <h2 className="text-center">Welcome back.</h2>
                                    <form onSubmit={handleSubmit(this.submitLogin)}>
                                        <div className="form-group">
                                            <fieldset className="form-group">
                                                <label>Email:</label>
                                                <input {...email} type="email" className="form-control"
                                                       id="exampleInputEmail1"
                                                       placeholder="Enter email"/>
                                            </fieldset>
                                        </div>
                                        <div className="form-group">
                                            <fieldset className="form-group">
                                                <label>Password:</label>
                                                <input {...password} type="password" className="form-control"
                                                       id="exampleInputPassword1"
                                                       placeholder="Password"/>
                                            </fieldset>
                                        </div>
                                        <button action="submit" className="btn btn-default">Sign in</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}

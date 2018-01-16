import React, { PureComponent } from 'react';
import { reduxForm } from 'redux-form';

import { signUp } from '../../actions/SignUpActions';


@reduxForm({
    form: 'signup',
    fields: ['email', 'password', 'passwordConfirm'],
    validate,
}, (state) => {
    state.signUp;
}, { signUp })
export default class SignUp extends PureComponent {
    constructor(props) {
        super(props);

        this.handleFormSubmit = this.handleFormSubmit.bind(this);
    }

    handleFormSubmit({ email, password }) {
        this.props.signUp(email, password, 'hardcode');
    }

    render() {
        const { handleSubmit, fields: { email, password, passwordConfirm } } = this.props;

        return (
            <div id="signup-page">
                <h1 className="text-center">Create new account</h1>
            <section id="signupform" className="outer-wrapper">
                <div className="inner-wrapper">
                        <div className="container">
                            <div className="row">
                            <div className="col-sm-4 col-sm-offset-4">
                                    <form onSubmit={handleSubmit(this.handleFormSubmit)}>
                                <fieldset className="form-group">
                                          <label>Email:</label>
                                          <input className="form-control" {...email} />
                                          {email.touched && email.error && <div className="error">{email.error}</div>}
                                        </fieldset>
                                <fieldset className="form-group">
                                            <label>Password:</label>
                                          <input className="form-control" {...password} type="password" />
                                            {password.touched && password.error &&
                                  <div className="error">{password.error}</div>}
                                        </fieldset>
                                <fieldset className="form-group">
                                            <label>Confirm Password:</label>
                                          <input className="form-control" {...passwordConfirm} type="password" />
                                            {passwordConfirm.touched && passwordConfirm.error &&
                                  <div className="error">{passwordConfirm.error}</div>}
                                        </fieldset>
                                        <button action="submit" className="btn btn-primary">Sign up!</button>
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

function validate(formProps) {
    const errors = {};

    if (!formProps.email) {
        errors.email = 'Please enter an email';
    }

    if (!formProps.password) {
        errors.password = 'Please enter a password';
    }

    if (!formProps.passwordConfirm) {
        errors.passwordConfirm = 'Please enter a password confirmation';
    }

    if (formProps.password !== formProps.passwordConfirm) {
        errors.password = 'Passwords must match';
    }

    return errors;
}

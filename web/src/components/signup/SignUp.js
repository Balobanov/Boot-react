import React, {PureComponent} from "react";

export default class SignUp extends PureComponent {

    render() {
        return (
            <div id="signup-page">
                <h1>SignUp</h1>
                <section id="loginform" className="outer-wrapper">
                    <div className="inner-wrapper">
                        <div className="container">
                            <div className="row">
                                <div className="col-sm-4 col-sm-offset-4">
                                    <h2 className="text-center">Create new account.</h2>
                                    <form role="form">
                                        <div className="form-group">
                                            <label for="exampleInputEmail1">Email address</label>
                                            <input type="email" className="form-control" id="exampleInputEmail1" placeholder="Enter email"/>
                                        </div>
                                        <div className="form-group">
                                            <label for="exampleInputPassword1">Password</label>
                                            <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Password"/>
                                        </div>
                                        <div className="form-group">
                                            <label for="exampleInputPassword1">Confirm password</label>
                                            <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Password"/>
                                        </div>
                                        <button type="submit" className="btn btn-default">Submit</button>
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

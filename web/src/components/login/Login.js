import React, {PureComponent} from "react";

export default class Login extends PureComponent {

    render() {
        return (
            <div id="login-page">
                <h1>Login</h1>
                <section id="loginform" className="outer-wrapper">

                    <div className="inner-wrapper">
                        <div className="container">
                            <div className="row">
                                <div className="col-sm-4 col-sm-offset-4">
                                    <h2 className="text-center">Welcome back.</h2>
                                    <form role="form">
                                        <div className="form-group">
                                            <label for="exampleInputEmail1">Email address</label>
                                            <input type="email" className="form-control" id="exampleInputEmail1" placeholder="Enter email"/>
                                        </div>
                                        <div className="form-group">
                                            <label for="exampleInputPassword1">Password</label>
                                            <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Password"/>
                                        </div>

                                        <div className="checkbox">
                                            <label>
                                               <input type="checkbox"/>  Remember me
                                            </label>
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

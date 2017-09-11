import React, {PureComponent} from "react";

import NavBar from "./navbar/NavBar";

export default class MainContainer extends PureComponent {

    render() {
        return (
            <div id="main-container">
                <NavBar/>
                <div id="content">
                    {this.props.children}
                </div>
                <footer>
                    <h1>Footer</h1>
                </footer>
            </div>
        );
    }
}
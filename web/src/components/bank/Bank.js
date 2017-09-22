import React, {PureComponent, Component} from "react";

export default class Bank extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const {bank} = this.props;
        return (
            <div id="bank-page">
                <div id="bank-name">
                    <label>{bank.name}</label>
                </div>
            </div>
        );
    }
}
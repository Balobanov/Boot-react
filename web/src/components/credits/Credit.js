import React, {PureComponent, Component} from "react";

export default class Credit extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const {credit} = this.props;
        let status = 'undefined';
        return (
            <div id="credit-page">
                <div id="credit-name">
                    <label>{credit.name}</label>
                    {
                        credit.status === 1
                            ? <div className='credit-status status-active'/>
                            : <div className='credit-status status-inactive'/>
                    }
                </div>
                <div id="credit-status">
                </div>
            </div>
        );
    }
}
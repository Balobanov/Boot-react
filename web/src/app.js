import React, { PureComponent } from 'react';
import ReactDOM from 'react-dom';

import './style/main.css';

class App extends PureComponent {

    render() {
        return (
            <h1>Supessrsss server</h1>
        );
    }
}

ReactDOM.render(<App />, document.getElementById('secret_bank_app'));
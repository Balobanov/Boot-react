import { combineReducers } from 'redux';
import { reducer as form } from 'redux-form';

import { loginReducer as login } from './reducers/LoginReducer';
import signUp from './reducers/SignUpReducer';
import auth from './reducers/AuthReducer';
import { banksReducer as banks } from './reducers/BankReducer';


const IndexRedusers = combineReducers({
    form,
    login,
    signUp,
    auth,
    banks,
});

export default IndexRedusers;

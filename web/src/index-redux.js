import { combineReducers } from 'redux';
import { reducer as form } from 'redux-form';

import {loginReducer as login} from './components/login/LoginReducer';
import signUp from './components/signup/SignUpReducer';
import auth from './components/auth/AuthReducer';
import {banksReducer as banks} from "./components/bank/BankReducer";


const IndexRedusers = combineReducers({
    form,
    login,
    signUp,
    auth,
    banks
});

export default IndexRedusers;
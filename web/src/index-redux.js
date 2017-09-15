import { combineReducers } from 'redux';
import { reducer as form } from 'redux-form';

import {loginReducer as login} from './components/login/LoginReducer';
import signUp from './components/signup/SignUpReducer';
import auth from './components/auth/AuthReducer';
import {creditsReducer as credits} from "./components/credits/CreditsReducer";


const IndexRedusers = combineReducers({
    form,
    login,
    signUp,
    auth,
    credits
});

export default IndexRedusers;
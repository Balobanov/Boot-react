import { combineReducers } from 'redux';
import { reducer as form } from 'redux-form';

import {loginReducer as login} from './components/login/reducer/LoginReducer';
import signUp from './components/signup/reducer/SignUpReducer';
import auth from './components/auth/AuthReducer';
import {creditsReducer as credits} from "./components/credits/reducer/CreditsReducer";


const IndexRedusers = combineReducers({
    form,
    login,
    signUp,
    auth,
    credits
});

export default IndexRedusers;
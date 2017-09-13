/**
 * Created by user on 11.09.17.
 */
import { combineReducers } from 'redux';
import { reducer as form } from 'redux-form';

import {loginReducer as login} from './components/login/reducer/LoginReducer';

const IndexRedusers = combineReducers({
    form,
    login
});

export default IndexRedusers;
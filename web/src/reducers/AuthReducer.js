import { fromJS } from 'immutable';

import {AUTH_ACCESS_TOKEN, AUTH_CLEAR} from "../constants/AuthConstants";
const initState = fromJS({
    access_token: null,
    token_type: null,
    refresh_token: null,
    expires_in: null,
    scope: null
});

const authReducer = (state = initState, action) => {

    switch (action.type) {

        case AUTH_ACCESS_TOKEN: {
            return state.merge(action.auth);
        }

        case AUTH_CLEAR: {
            return initState;
        }

        default:
            return state;
    }
};

export default authReducer;
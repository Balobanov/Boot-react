import { fromJS } from 'immutable';

import { LOGIN_ERROR, LOGIN_REQUESTING, LOGIN_SUCCESS } from '../constants/LoginConstants';

/**
 * @type {{requesting: boolean, successful: boolean, messages: Array, errors: Array}}
 */
const initialState = fromJS({
    requesting: false,
    successful: false,
    messages: [],
    errors: [],
});

/**
 * TODO: add immutable.js
 */
export const loginReducer = (state = initialState, action) => {
    switch (action.type) {
    case LOGIN_REQUESTING: {
        return state
            .set('successful', false)
            .set('requesting', true)
            .update('messages', messages => messages.push({ message: 'Logging in...', time: new Date() }))
            .set('requesting', true);
    }

    case LOGIN_SUCCESS: {
        return state
            .set('successful', true)
            .set('requesting', false)
            .setIn(['errors'], fromJS([]))
            .setIn(['messages'], fromJS([{ message: 'Successfully', time: new Date() }]));
    }

    case LOGIN_ERROR: {
        return state
            .set('requesting', false)
            .set('successful', false)
            .setIn(['messages'], fromJS([]))
            .update('errors', messages => messages.push({ message: action.error.message }));
    }

    default: {
        return state;
    }
    }
};

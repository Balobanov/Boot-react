import { fromJS } from 'immutable';

import { SIGN_UP_FAILED, SIGN_UP_REQUESTING, SIGN_UP_SUCCESS } from '../constants/SignUpConstants';

const initialState = fromJS({
    requesting: false,
    successful: false,
    messages: [],
    errors: [],
});

const reducer = (state = initialState, action) => {
    switch (action.type) {
    case SIGN_UP_REQUESTING: {
        return state
            .set('successful', false)
            .set('requesting', true)
            .update('messages', messages => messages.push({ message: 'Signing up...', time: new Date() }))
            .set('requesting', true);
    }

    case SIGN_UP_SUCCESS: {
        return state
            .set('successful', true)
            .set('requesting', false)
            .setIn(['errors'], fromJS([]))
            .setIn(['messages'], fromJS([{ message: 'Successfully created account.', time: new Date() }]));
    }

    case SIGN_UP_FAILED: {
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

export default reducer;

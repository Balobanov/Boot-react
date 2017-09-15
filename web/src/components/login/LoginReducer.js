import {LOGIN_ERROR, LOGIN_REQUESTING, LOGIN_SUCCESS} from "./LoginConstants";

/**
 * @type {{requesting: boolean, successful: boolean, messages: Array, errors: Array}}
 */
const initialState = {
    requesting: false,
    successful: false,
    messages: [],
    errors: [],
};

/**
 * TODO: add immutable.js
 */
export const loginReducer = (state = initialState, action) => {
    switch (action.type) {

        case LOGIN_REQUESTING: {
            return {
                requesting: true,
                successful: false,
                messages: [{body: 'Logging in...', time: new Date()}],
                errors: [],
            };
        }

        case LOGIN_SUCCESS: {
            return {
                errors: [],
                messages: [],
                requesting: false,
                successful: true,
            };
        }

        case LOGIN_ERROR:
            return {
                errors: state.errors.concat([{
                    body: action.error.toString(),
                    time: new Date(),
                }]),
                messages: [],
                requesting: false,
                successful: false,
            };

        default: {
            return state;
        }
    }
};
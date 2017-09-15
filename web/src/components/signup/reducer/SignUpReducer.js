import {
    SIGN_UP_REQUESTING,
    SIGN_UP_SUCCESS,
    SIGN_UP_FAILED
} from "../constants/SignUpConstants";

const initialState = {
    requesting: false,
    successful: false,
    messages: [],
    errors: [],
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case SIGN_UP_REQUESTING:
            return {
                requesting: true,
                successful: false,
                messages: [{ body: 'Signing up...', time: new Date() }],
                errors: [],
            };

        case SIGN_UP_SUCCESS:
            return {
                errors: [],
                messages: [{
                    body: `Successfully created account`,
                    time: new Date(),
                }],
                requesting: false,
                successful: true,
            };

        case SIGN_UP_FAILED:
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

export default reducer;

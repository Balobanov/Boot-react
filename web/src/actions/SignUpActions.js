import {SIGN_UP_REQUESTING} from "../constants/SignUpConstants";

export const signUp = (email, password, code) => {
    return {
        type: SIGN_UP_REQUESTING,
        email,
        password,
        code
    }
};
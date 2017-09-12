import { LOGIN_REQUESTING } from '../constants/LoginConstants';

export const loginRequest = (email, password)=> {
    return {
        type: LOGIN_REQUESTING,
        email,
        password
    }
};
import { LOGIN_REQUESTING, LOGIN_SUCCESS, LOGIN_ERROR } from './LoginConstants';

export const loginRequest = (email, password)=> {
    return {
        type: LOGIN_REQUESTING,
        email,
        password
    }
};

export const loginSuccess = ()=> {
    return {
        type: LOGIN_SUCCESS,
    }
};

export const loginFailed = (error)=> {
    return {
        type: LOGIN_ERROR,
        error
    }
};
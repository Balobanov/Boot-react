import { LOGIN_REQUESTING, LOGIN_SUCCESS, LOGIN_ERROR } from '../constants/LoginConstants';

export const loginRequest = (email, password) => ({
    type: LOGIN_REQUESTING,
    email,
    password
});

export const loginSuccess = () => ({
    type: LOGIN_SUCCESS
});

export const loginFailed = error => ({
    type: LOGIN_ERROR,
    error
});

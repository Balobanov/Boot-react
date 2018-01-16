import { LOGIN_FACEBOOK_ERROR, LOGIN_FACEBOOK_REQUESTING, LOGIN_FACEBOOK_SUCCESS } from '../constants/FacebookConstants';

export const facebookLoginRequest = token => ({
    type: LOGIN_FACEBOOK_REQUESTING,
    token
});

export const facebookLoginFaile = () => ({
    type: LOGIN_FACEBOOK_ERROR
});

export const facebookLoginSuccess = () => ({
    type: LOGIN_FACEBOOK_SUCCESS
});

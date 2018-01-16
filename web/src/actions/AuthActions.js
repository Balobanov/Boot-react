import { AUTH_ACCESS_TOKEN, AUTH_CLEAR } from '../constants/AuthConstants';

export const authToken = auth => ({
    type: AUTH_ACCESS_TOKEN,
    auth
});

export const authClear = () => ({
    type: AUTH_CLEAR
});

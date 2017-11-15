import {LOGIN_FACEBOOK_ERROR, LOGIN_FACEBOOK_REQUESTING, LOGIN_FACEBOOK_SUCCESS} from "../constants/FacebookConstants";

export const facebookLoginRequest = (token) => {
  return {
      type: LOGIN_FACEBOOK_REQUESTING,
      token
  }
};

export const facebookLoginFaile = () => {
    return {
        type: LOGIN_FACEBOOK_ERROR
    }
};

export const facebookLoginSuccess = () => {
    return {
        type: LOGIN_FACEBOOK_SUCCESS
    }
};
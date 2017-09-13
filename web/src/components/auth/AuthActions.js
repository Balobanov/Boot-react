import {AUTH_ACCESS_TOKEN, AUTH_CLEAR} from "./AuthConstants";

export const authToken = (auth) => {
  return {
      type: AUTH_ACCESS_TOKEN,
      auth
  };
};

export const authClear = () => {
    return {
        type: AUTH_CLEAR
    };
};
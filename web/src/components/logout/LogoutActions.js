import {LOGOUT_REQUESTING} from "./LogoutConstants";

export const logoutRequest = () => {
  return {
    type: LOGOUT_REQUESTING
  };
};
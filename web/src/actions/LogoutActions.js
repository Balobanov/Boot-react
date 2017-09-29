import {LOGOUT_REQUESTING} from "../constants/LogoutConstants";

export const logoutRequest = () => {
  return {
    type: LOGOUT_REQUESTING
  };
};
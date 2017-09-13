import {CREDITS_REQUEST, CREDITS_FAILED, CREDITS_SUCCESS} from "../constants/CreditsConstants";

const initState = {
  credits: [],
  errors: []
};

export const creditsReducer = (state = initState, action) => {
  switch (action.type) {

      case CREDITS_SUCCESS: {
          return {
              credits: action.credits,
              errors: action.errors
          };
      }

      case CREDITS_FAILED: {
          return {};
      }

      default: {
          return state;
      }
  }
};
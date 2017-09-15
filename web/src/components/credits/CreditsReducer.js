import {CREDITS_REQUEST, CREDITS_FAILED, CREDITS_SUCCESS, CREDITS_UPDATE} from "./CreditsConstants";

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

      case CREDITS_UPDATE: {
          let credits = state.credits;
          let errors = {};

          credits.forEach(c => {
              if(c.name === action.credit.name)
                  c.status = action.credit.status
          });
          return {
              credits,
              errors
          };
      }

      default: {
          return state;
      }
  }
};
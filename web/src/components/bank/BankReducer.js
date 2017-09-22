import {BANKS_REQUEST, BANKS_FAILED, BANKS_SUCCESS, BANKS_UPDATE} from "./BankConstants";

const initState = {
  banks: [],
  errors: []
};

export const banksReducer = (state = initState, action) => {
  switch (action.type) {

      case BANKS_SUCCESS: {
          return {
              banks: action.banks,
              errors: action.errors
          };
      }

      case BANKS_FAILED: {
          return {};
      }

      case BANKS_UPDATE: {
          let banks = state.banks;
          let errors = {};

          banks.forEach(b => {
              if(b.name === action.bank.name)
                  b.status = action.bank.name
          });
          return {
              banks,
              errors
          };
      }

      default: {
          return state;
      }
  }
};
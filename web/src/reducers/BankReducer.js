import { fromJS } from 'immutable';

import {BANKS_REQUEST, BANKS_FAILED, BANKS_SUCCESS, BANKS_UPDATE, BANKS_EDIT} from "../constants/BankConstants";

const initState = fromJS({
    banks: [],
    errors: [],
    selected: null
});

export const banksReducer = (state = initState, action) => {
  switch (action.type) {

      case BANKS_SUCCESS: {
          return state.set('banks', fromJS(action.banks));
      }

      case BANKS_FAILED: {
          return state.set('errors', fromJS(action.errors));
      }

      case BANKS_UPDATE: {
          return state.updateIn(['banks'], banks => banks.set(state.get('banks').findIndex(m => m.get('id') === action.id), fromJS({id: action.id, name: action.name})))
              .set('selected', null);
      }

      case BANKS_EDIT: {
          return state.set('selected', state.get('banks').find(e => {
              return action.id === e.get('id');
          }));
      }

      default: {
          return state;
      }
  }
};
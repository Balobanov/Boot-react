import * as  types from "./../../../types/error/error";

const state = {
  error: {
    errorMsg: null,
    errorCode: null
  }
};

const getters = {
  [types.ERROR_GET_ERROR_CODE](state){
    return state.error.errorCode;
  },

  [types.ERROR_GET_ERROR_MSG](state){
    return state.error.errorMsg;
  }
};

const mutations = {
  [types.ERROR_SET_ERROR_CODE](state, payload) {
    state.error.errorCode = payload;
  },

  [types.ERROR_SET_ERROR_MSG](state, payload) {
    state.error.errorMsg = payload;
  },

  [types.ERROR_RESET_ERROR](state){
    state.error.errorCode = null;
    state.error.errorMsg = null;
  }
};

const actions = {};

export const error = {
  state,
  getters,
  actions,
  mutations
};

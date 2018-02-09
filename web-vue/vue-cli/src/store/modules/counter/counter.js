import * as types from "./../../../types/counter/counter"

const state = {
  count: 0,
  increasing: false
};

const getters = {
  [types.COUNTER_GET_COUNTER](state){
    return state.count;
  },

  [types.COUNTER_GET_INCREASING](state){
    return state.increasing;
  }
};

const mutations = {
  [types.COUNTER_INCREASE](state, increaseBy){
    state.count += +increaseBy;
  },

  [types.COUNTER_FETCHING_START](state) {
    state.increasing = true;
  },

  [types.COUNTER_FETCHING_DONE](state) {
    state.increasing = false;
  }
};

const actions = {
  [types.COUNTER_ASYNC_INCREASE_BY]({commit}, payload) {
    commit(types.COUNTER_FETCHING_START);
    setTimeout(() => {
      commit(types.COUNTER_INCREASE, payload);
      commit(types.COUNTER_FETCHING_DONE);
    }, 1500);
  }
};

export const counter = {
  state,
  getters,
  mutations,
  actions
};

import * as types from "./../../../types/counter/counter"
import {router} from './../../../router/router';

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

  [types.COUNTER_SET_COUNTER](state, counter){
    state.count = +counter;
  },

  [types.COUNTER_FETCHING_START](state) {
    state.increasing = true;
  },

  [types.COUNTER_FETCHING_DONE](state) {
    state.increasing = false;
  }
};

const actions = {
  async [types.COUNTER_ASYNC_INCREASE_BY]({commit}, payload) {
    commit(types.COUNTER_FETCHING_START);
    await fetch(`/counter`, {
      method: 'POST',
      body: JSON.stringify({by: payload}),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.state.authorization.authorization.auth.access_token}`
      }
    });
    commit(types.COUNTER_INCREASE, payload);
    commit(types.COUNTER_FETCHING_DONE);
  },

  async [types.COUNTER_ASYNC_FETCH]({commit}) {
    commit(types.COUNTER_FETCHING_START);
    let response = await fetch(`/counter`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.state.authorization.authorization.auth.access_token}`
      }
    });

    let count = await response.json();

    commit(types.COUNTER_SET_COUNTER, count.count);
    commit(types.COUNTER_FETCHING_DONE);
  }
};

export const counter = {
  state,
  getters,
  mutations,
  actions
};

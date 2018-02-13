import * as types from "./../../../types/auth/auth";
import {saveAuthToLocalStore} from "./../../../utils/utils";
import {router} from './../../../router/router';

const state = {
  authorization: {
    logging: false,
    auth: {
      access_token: null,
      refresh_token: null,
      token_type: null,
      expires_in: 0,
      scope: null
    },
    error: null,
    tokenReceiveDate: null,
    user: {}
  }
};

const mutations = {

  [types.MUTATIONS_SET_USER](state, user){
    state.authorization.user = user;
  },

  [types.MUTATIONS_SET_LOGGING](state, logging){
    state.authorization.logging = logging;
  },

  [types.MUTATIONS_SET_AUTH](state, payload){
    state.authorization.auth = payload;
    state.authorization.tokenReceiveDate = new Date();
  },

  [types.MUTATIONS_SET_ERROR](state, error){
    state.authorization.error = error;
  }
};

const actions = {
  [types.ACTIONS_START_LOGIN_PROCESS]({commit}, payload){
    commit(types.MUTATIONS_SET_LOGGING, true);
    fetch(`/oauth/token?grant_type=password&username=${payload.email}&password=${payload.password}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Basic ${btoa('clientapp:123456')}`,
      },
    })
      .then(response => response.json())
      .then(json => {
        saveAuthToLocalStore(json);
        commit(types.MUTATIONS_SET_AUTH, json);
        commit(types.MUTATIONS_SET_LOGGING, false);
        router.push('/');
      })
      .catch((error) => {
        commit(types.MUTATIONS_SET_LOGGING, false);
        commit(types.MUTATIONS_SET_ERROR, error);
      });

  },

  async [types.ACTIONS_START_LOGIN_PROCESS_BOX]({commit}, code){
    commit(types.MUTATIONS_SET_LOGGING, true);

    try {
      let formData = new FormData();
      formData.append('grant_type', 'authorization_code');
      formData.append('client_id', '8p130uxdvlivu7j9171if1nxsa5s6b9p');
      formData.append('code', code);
      formData.append('client_secret', 'stobj1z95e09isdBpA0MOQZ0Wk7XrBOd');

      let response = await fetch(`https://api.box.com/oauth2/token`, {
        method: 'POST',
        body: formData,
        headers: {
          'Access-Control-Allow-Origin': '*'
        }
      });

      let json = await response.json();

      let authResponse = await fetch('/box/registration', {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
          'Content-Type': 'application/json'
        },
      });

      let auth = await authResponse.json();

      commit(types.MUTATIONS_SET_AUTH, auth);
      saveAuthToLocalStore(auth);
      router.push('/');
    }
    catch (e) {
      commit(types.MUTATIONS_SET_LOGGING, false);
    }
    commit(types.MUTATIONS_SET_LOGGING, false);
  }
};

const getters = {

  [types.GETTERS_GET_USER](state){
    return state.authorization.user;
  },

  [types.GETTERS_GET_ACCESS_TOKEN](state) {
    return state.authorization.auth.access_token;
  },

  [types.GETTERS_GET_REFRESH_TOKEN](state) {
    return state.authorization.auth.refresh_token;
  },

  [types.GETTERS_GET_TOKEN_TYPE](state) {
    return state.authorization.auth.token_type;
  },

  [types.GETTERS_GET_EXPIRES_IN](state) {
    return state.authorization.auth.expires_in;
  },

  [types.GETTERS_GET_TOKEN_SCOPE](state) {
    return state.authorization.auth.scope;
  },

  [types.GETTERS_GET_TOKEN_RECEIVE_DATE](state) {
    return state.authorization.tokenReceiveDate;
  },

  [types.GETTERS_GET_LOGGING](state) {
    return state.authorization.logging;
  },

  [types.GETTERS_GET_ERROR](state) {
    return state.authorization.error;
  }
};

export const authorization = {
  state,
  getters,
  actions,
  mutations
};

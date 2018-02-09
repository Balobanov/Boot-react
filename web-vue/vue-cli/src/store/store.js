import Vue from "vue";
import Vuex from "vuex";
import {counter} from "./modules/counter/counter";
import {error} from "./modules/errors/error";

Vue.use(Vuex);

export const store = new Vuex.Store({
  modules: {
    counter,
    error
  }
});

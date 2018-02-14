import Vue from "vue";
import App from "./App.vue";
import {store} from "./store/store";
import Spinner from "./components/spinner/Spinner.vue";
import {router} from './router/router';
import 'babel-polyfill'; // for async await
Vue.component('spinner', Spinner);

new Vue({
  el: '#app',
  render: h => h(App),
  store,
  router
});

import Home from './../components/home/HomeView.vue';
import NotFound from './../components/404/NotFoundView.vue';
import Counter from './../components/counter/CounterView.vue';
import Login from './../components/login/LoginView.vue';
import Vue from 'vue';
import VueRouter from 'vue-router';

const routes = [
  {path: '/', component: Home},
  {path: '/counter', component: Counter},
  {path: '/login', component: Login},
  {path: '*', component: NotFound},
];

Vue.use(VueRouter);

export const router = new VueRouter({routes});

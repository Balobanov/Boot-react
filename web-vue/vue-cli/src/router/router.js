import NotFound from './../components/404/NotFoundView.vue';
import Dashboard from './../components/Dashboard/Dashboard.vue';
import MainContainer from './../components/MainContainer/MainContainer.vue';
import Login from './../components/Login/LoginView.vue';
import Vue from 'vue';
import VueRouter from 'vue-router';

const routes = [
  {path: '/', redirect: '/login'},
  {path: '/dashboard', component: MainContainer, children: [
    {path: '', component: Dashboard}
  ]},
  {path: '/login', component: Login},
  {path: '*', component: NotFound},
];

Vue.use(VueRouter);

export const router = new VueRouter({routes, mode: 'history'});

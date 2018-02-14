<template>
  <div>
    <p>Counter App</p>
    <router-link to="/counter" exact active-class="active"><a>Counter</a></router-link>
  </div>
</template>

<script>
  import {mapMutations, mapActions} from 'vuex';
  import {MUTATIONS_SET_AUTH} from './../../types/auth/auth';


  export default {

    methods: {
      ...mapMutations({
        setToken: MUTATIONS_SET_AUTH
      })
    },

    beforeRouteEnter (to, from, next) {
      if (localStorage.getItem('auth')) {
        let auth = JSON.parse(localStorage.getItem('auth'));
        next(vm => {
          vm.setToken(auth);
        });
      } else {
        next('/login');
      }
    },
  }
</script>

<style>

</style>

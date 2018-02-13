<template>
  <div>
    <h1>Login to app</h1>
    <div>
      <form>
        <div>
          <input type="email" placeholder="Enter your email here" v-model="login.email"/>
        </div>
        <div>
          <input type="password" placeholder="Enter your password here" v-model="login.password"/>
        </div>
        <button @click.prevent="submit">Login</button>
        <a href="https://account.box.com/api/oauth2/authorize?response_type=code&client_id=8p130uxdvlivu7j9171if1nxsa5s6b9p">Login via BOX</a>
      </form>
    </div>

  </div>
</template>

<script>

  import {mapActions} from 'vuex';
  import {ACTIONS_START_LOGIN_PROCESS, ACTIONS_START_LOGIN_PROCESS_BOX} from './../../types/auth/auth';

  export default{
    data(){
      return {
        login: {
          email: '',
          password: ''
        }
      }
    },
    methods: {
      ...mapActions({
        startLogin: ACTIONS_START_LOGIN_PROCESS,
        startLoginBox: ACTIONS_START_LOGIN_PROCESS_BOX
      }),

      submit() {
        this.startLogin(this.login);
      }
    },

    mounted() {
      if(this.$route.query.code) {
          this.startLoginBox(this.$route.query.code);
      }
    }
  }
</script>

<style>

</style>

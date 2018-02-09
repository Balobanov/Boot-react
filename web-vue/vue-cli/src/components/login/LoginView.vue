<template>
  <div>
    <h1>Login to app</h1>
    <form>
      <input type="email" placeholder="Enter your email here" v-model="login.email"/>
      <input type="password" placeholder="Enter your password here" v-model="login.password"/>
      <button @click.prevent="submit">Login</button>
    </form>
  </div>
</template>

<script>
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
      submit() {
        fetch(`/oauth/token?grant_type=password&username=${this.login.email}&password=${this.login.password}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${btoa('clientapp:123456')}`,
          }
        })
          .then(response => response.json())
          .then(json => console.log(json));
      }
    }
  }
</script>

<style>

</style>

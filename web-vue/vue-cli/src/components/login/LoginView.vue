<template>
  <div>
    <h1>Login to app</h1>
    <form>
      <input type="email" placeholder="Enter your email here"/>
      <input type="password" placeholder="Enter your password here"/>
      <button @click.prevent="login">Login</button>
    </form>
  </div>
</template>

<script>
  export default{
      data(){
        return{

        }
      },
      methods: {
          login() {
            alert('sddsdsds');
          }
      }
  }
</script>

<style>

</style>

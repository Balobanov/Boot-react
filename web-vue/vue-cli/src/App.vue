<template>
  <div id="app-vue-bank">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'app-vue-bank',
  }
</script>

<style>
  #app-vue-bank {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #000000;
    margin-top: 60px;
  }

  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #b95764;
  }
</style>

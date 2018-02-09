<template>
  <div>
    <counter-control></counter-control>
    <counter-result-view></counter-result-view>
  </div>
</template>

<script>
  import CounterControl from './../counter/CounterControlView.vue';
  import CounterResultView from './../counter/CounterResultView.vue';

  export default {
    components: {
      CounterControl,
      CounterResultView
    },
    beforeRouteEnter (to, from, next) {
      if (localStorage.getItem('access_token')) {
        next();
      } else {
        next('/login');
      }
    },
  }
</script>

<style>

</style>

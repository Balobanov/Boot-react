<template>
  <div>
    <counter-control></counter-control>
    <counter-result-view></counter-result-view>
  </div>
</template>

<script>
  import {mapActions} from 'vuex';
  import {COUNTER_ASYNC_FETCH} from './../../types/counter/counter';

  import CounterControl from './../counter/CounterControlView.vue';
  import CounterResultView from './../counter/CounterResultView.vue';

  export default {
    methods: {
      ...mapActions({
        fetchCounter: COUNTER_ASYNC_FETCH
      })
    },
    components: {
      CounterControl,
      CounterResultView
    },
    beforeRouteEnter (to, from, next) {
      if (localStorage.getItem('auth')) {
        next(vm => {
          vm.fetchCounter();
        });
      } else {
        next('/login');
      }
    },
  }
</script>

<style>

</style>

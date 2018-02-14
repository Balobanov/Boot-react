<template>
  <div>
    <div v-if="!fetching">
      <counter-control></counter-control>
      <counter-result-view></counter-result-view>
    </div>
    <div v-if="fetching">
      <spinner></spinner>
    </div>
  </div>
</template>

<script>
  import {mapActions, mapGetters} from 'vuex';
  import {COUNTER_ASYNC_FETCH, COUNTER_GET_FETCHING, COUNTER_GET_INCREASING} from './../../types/counter/counter';

  import CounterControl from './../counter/CounterControlView.vue';
  import CounterResultView from './../counter/CounterResultView.vue';

  export default {
    methods: {
      ...mapActions({
        fetchCounter: COUNTER_ASYNC_FETCH
      }),

    },
    computed: {
      ...mapGetters({
        fetching: COUNTER_GET_FETCHING
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

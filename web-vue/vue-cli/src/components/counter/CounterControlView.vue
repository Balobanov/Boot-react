<template>
  <div>
    <input type="number" placeholder="Increase by" v-model="incBy"/>
    <span v-if="!increasing">
      <button @click="asyncIncreaseByInnerMethod(incBy)">asyncIncreaseBy</button>
      <button @click="asyncIncreaseBy(incBy)">asyncIncreaseByInnerMethod</button>
    </span>
  </div>
</template>

<script>
  import {mapActions, mapGetters} from 'vuex';
  import {COUNTER_GET_INCREASING} from './../../types/counter/counter';

  import * as types from './../../types/counter/counter';

  export default {
    data() {
      return {
        incBy: 1
      }
    },
    computed: {
      ...mapGetters({
        increasing: COUNTER_GET_INCREASING
      })
    },
    methods: {
      ...mapActions({
        asyncIncreaseBy: types.COUNTER_ASYNC_INCREASE_BY
      }),

      asyncIncreaseByInnerMethod(incBy) {
        this.asyncIncreaseBy(incBy)
      }
    }
  }
</script>

<style>

</style>

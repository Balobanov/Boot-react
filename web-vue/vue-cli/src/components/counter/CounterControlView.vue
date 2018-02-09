<template>
  <div>
    <input type="number" placeholder="Increase by" v-model="incBy"/>
    <button @click="asyncIncreaseByInnerMethod(incBy)">asyncIncreaseBy</button>
    <button @click="asyncIncreaseBy(incBy)">asyncIncreaseByInnerMethod</button>
  </div>
</template>

<script>
  import {mapActions} from 'vuex';

  import * as types from './../../types/counter/counter';

  export default {
    data() {
      return {
        incBy: 1
      }
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

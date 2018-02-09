<template>
  <div>
    <div>
      <spinner v-if="increasing"></spinner>
    </div>
    <div v-if="!increasing">
      <p>{{ count }}</p>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex';

  import * as types from './../../types/counter/counter';

  export default {
    computed: {
      ...mapGetters({
          count: types.COUNTER_GET_COUNTER,
          increasing: types.COUNTER_GET_INCREASING
        }
      )
    }
  }
</script>

<style>

</style>

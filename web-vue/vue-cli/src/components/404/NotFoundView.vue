<template>
  <div>
    <div v-if="false">
      <h1>Error: ****</h1>
    </div>
    <div v-else>
      <h1>Ooops...</h1>
      <h2>Something went wrong</h2>
    </div>
  </div>
</template>

<script>

  import {mapMutations} from 'vuex';
  import {ERROR_RESET_ERROR} from './../../types/error/error';

  export default {

    methods: {
      ...mapMutations({
        resetErrorState: ERROR_RESET_ERROR
      })
    },

    beforeDestroy(){
      this.resetErrorState();
    }
  }


</script>

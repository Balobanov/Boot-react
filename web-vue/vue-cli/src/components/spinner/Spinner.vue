<template>
  <div class="loader"></div>
</template>

<script>
export default {

}
</script>

<style scoped="">
  .loader {
    border-top: 16px solid #ff0000;
    border-right: 16px solid #ffff00;
    border-bottom: 16px solid #00ff00;
    border-left: 16px solid #0000fa;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    animation: spin 2s linear infinite;
    margin: 10px auto;

  }

  @keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
  }
</style>

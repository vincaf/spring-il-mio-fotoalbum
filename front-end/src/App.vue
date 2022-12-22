<template>
  <div id="app">
      <HeaderWeb />
      <MainWeb />
  </div>
</template>

<script>
import MainWeb from './components/MainWeb.vue';
import HeaderWeb from './components/partials/HeaderWeb.vue'

export default {
  name: 'App',
  components: {
    MainWeb,
    HeaderWeb,
  }
}
</script>

<style lang="scss">

</style>

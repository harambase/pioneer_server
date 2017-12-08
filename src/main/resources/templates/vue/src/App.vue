<template>
  <div id="app">
    <leftSlider></leftSlider>
    <div id="rightConetnt">
      <topUser></topUser>
      <router-view></router-view>
    </div>

  </div>
</template>

<script>
  import leftSlider from './components/leftSlider.vue'
  import topUser from './components/topUser.vue'
  export default {
    name: 'app',
    components: {
      leftSlider,
      topUser
    }
  }
</script>

<style>
#rightConetnt{
  flex: 1;
  height: 100%;
  background-color: #f4f7f9;
  overflow-y: auto;
}
</style>

<template>
  <div id="leftSlider">
    <div class="logo">
      <img src="../assets/sliderLogo.png" alt="">
    </div>
    <ul class="navWrap">
      <template v-for="(item,index) in navList">
        <sliderItem :info="item"></sliderItem>
      </template>
    </ul>
  </div>
</template>

<script>
  import sliderItem from './leftSlider/sliderItem.vue'

  export default {
    name: 'leftSlider',
    components: {
      sliderItem
    },
    data: function () {
      return {
        navList: [
          {
            text: '本地仓库',
            icon: './static/nav1.png',
            iconActive: './static/nav1_active.png',
            linkTo: 'localStorage'
          },
          {
            text: '中央仓库',
            icon: './static/nav2.png',
            iconActive: './static/nav2_active.png',
            linkTo: 'centerStorage'
          },
          {
            text: '用户中心',
            icon: './static/nav3.png',
            iconActive: './static/nav3_active.png',
            linkTo: 'userCenter'
          },
          {
            text: '消息中心',
            icon: './static/nav4.png',
            iconActive: './static/nav4_active.png',
            linkTo: 'messageCenter'
          }
        ]
      }
    }
  }
</script>

<style>
  #leftSlider {
    height: 100%;
    flex: 0 0 300px;
    width: 300px;
    min-height: 100%;
    background: #2c4167;
  }

  #leftSlider .logo {
    text-align: center;
    color: #fff;
    margin-top: 40px;
  }

  #leftSlider ul.navWrap {
    width: 150px;
    margin: 0 auto;
    padding-top: 50px;
  }
</style>

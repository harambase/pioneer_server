<template>
  <router-link :to="{name:info.linkTo}">
    <li class="sliderItem" :class="[$route.name == info.linkTo?'active':'']">
      <div class="iconImg">
        <img v-if="$route.name !== info.linkTo" :src="info.icon" alt="">
        <img v-else="" :src="info.iconActive" alt="">
      </div>
      <p>{{info.text}}</p>
      <span v-if="$route.name == info.linkTo" class="activeDot"></span>
    </li>
  </router-link>

</template>

<script>
  export default {
    name: 'sliderItem',
    props: {
      info: Object
    },
    data: function () {
      return {}
    }
  }
</script>

<style>
  li.sliderItem {
    position: relative;
    margin-bottom: 40px;
    text-align: center;
    padding-top: 30px;
    padding-bottom: 25px;
  }

  li.sliderItem.active {
    background: #21314d;
  }

  li.sliderItem.active p {
    color: #fff;
  }

  li.sliderItem p {
    color: #768aab;
    font-size: 14px;
  }

  li.sliderItem span.activeDot {
    position: absolute;
    top: 10px;
    right: 10px;
    display: inline-block;
    width: 10px;
    height: 10px;
    border-radius: 5px;
    background: #3bcad8;
  }
</style>

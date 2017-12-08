<template>
  <div id="topUser">
    <h3 class="title">{{$route.meta.title}}</h3>
    <div class="user">
      <span class="userName">userName</span>
      <span @click="isUserMenuShow = !isUserMenuShow" class="slideDownBtn"><Icon type="chevron-down"></Icon></span>
      <span class="userImg">
        <img src="" alt="">
      </span>
      <div v-if="isUserMenuShow" class="sliderWrap">
        <p @click="isUserMenuShow = false" v-for="item in userItem">
          <router-link :to="item.linkTo">{{item.text}}</router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'topUser',
    data () {
      return {
        isUserMenuShow: false,
        userItem: [
          {
            text: '账号信息',
            linkTo: '/personalCenter/accountInfo'
          }, {
            text: '个人信息',
            linkTo: '/personalCenter/personalInfo'
          }, {
            text: '修改密码',
            linkTo: '/personalCenter/changePwd'
          }
        ]
      }
    },
    methods: {}
  }
</script>

<style>
  #topUser {
    height: 100px;
    background: #fff;
    box-shadow: 0 1px 20px rgba(4, 0, 0, 0.1);
  }

  #topUser h3.title {
    color: #4f5d66;
    font-size: 16px;
    float: left;
    font-weight: normal;
    padding-right: 20px;
    border-right: 2px solid #e1e4e8;
    margin-top: 40px;
    margin-left: 60px;
  }

  #topUser .user {
    position: relative;
    float: right;
    margin-right: 50px;
    margin-top: 20px;
  }

  #topUser .userName {
    color: #7ba0bb;
    font-size: 14px;
    margin-right: 20px;
  }

  #topUser .slideDownBtn {
    cursor: pointer;
    margin-right: 20px;
  }

  #topUser .userImg {
    display: inline-block;
    width: 40px;
    height: 40px;
    border-radius: 20px;
    background: skyblue;
    overflow: hidden;
    vertical-align: middle;
  }

  #topUser .sliderWrap {
    position: absolute;
    top: 35px;
    left: -60px;
    width: 180px;
    box-shadow: 0 2px 8px rgba(34, 52, 94, 0.4);
    background: #eeeeee;
  }

  #topUser .sliderWrap:after {
    position: absolute;
    top: -7px;
    right: 18px;
    content: "";
    border-left: 7px solid transparent;
    border-right: 7px solid transparent;
    border-bottom: 7px solid #eeeeee;
  }

  #topUser .sliderWrap > p > a {
    display: block;
    height: 35px;
    color: #6e818f;
    font-size: 12px;
    text-align: center;
    line-height: 35px;
    cursor: pointer;
  }

  #topUser .sliderWrap > p > a:hover {
    background: #3d8fc7;
    color: #fff;
  }

</style>

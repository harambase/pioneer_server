<template>
  <div id="userCenterDetail" v-show="userdetail">
    <div class="userCenterDetailWrap">
      <h3 class="userCenterDetailWrapTitle">添加新用户 <span @click="closeUserdetail" class="iconfont icon-guanbi"></span>
      </h3>
      <div class="personalTab">
        <ul>
          <li :class="[$route.path == item.linkTo?'active':'']" v-for="item in userItem">
            <router-link :to="item.linkTo">{{item.text}}</router-link>
          </li>
        </ul>
      </div>
      <router-view v-on:closedetail="closeUserdetail"></router-view>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'personalCenter',
    props: [
      'userdetail'
    ],
    data () {
      return {
        userItem: [
          {
            text: '账号信息',
            linkTo: '/userCenter/accountInfo'
          }, {
            text: '个人信息',
            linkTo: '/userCenter/personalInfo'
          }, {
            text: '修改密码',
            linkTo: '/userCenter/changePwd'
          }
        ]
      }
    },
    mounted: function () {
//      console.log(this.$route)
    },
    methods: {
      closeUserdetail () {
        this.$emit('showdetail', false)
        this.$router.back()
      }
    }
  }
</script>
<style>
  #userCenterDetail {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(55, 55, 55, .6);
  }

  #userCenterDetail .userCenterDetailWrap {
    width: 1200px;
    height: 890px;
    margin: 20px auto;
    background: #fff;
    box-shadow: 0 2px 1px rgba(44, 65, 103, 0.12);
  }

  #userCenterDetail .userCenterDetailWrap .userCenterDetailWrapTitle {
    height: 60px;
    line-height: 60px;
    padding-left: 20px;
    color: #4f5d66;
    font-size: 14px;
    border-bottom: 1px solid #ececec;
    font-weight: normal;
  }

  #userCenterDetail .userCenterDetailWrap .userCenterDetailWrapTitle > span.iconfont {
    float: right;
    margin-right: 20px;
    cursor: pointer;
  }

  #userCenterDetail .personalTab {
    height: 60px;
    background: #fff;
    box-shadow: 0 10px 20px rgba(44, 65, 103, 0.07);
  }

  #userCenterDetail .personalTab ul {
    padding-top: 20px;
  }

  #userCenterDetail .personalTab ul li {
    float: left;
    width: 160px;
    text-align: center;
    border-right: 2px solid #dcdcdc;
  }

  #userCenterDetail .personalTab ul li.active a {
    color: #03afc5;
    font-weight: bold;
  }

  #userCenterDetail .personalTab ul li a {
    display: block;
    color: #6e818f;
    font-size: 14px;
  }
</style>

<template>
  <div id="personalCenter">
    <div class="personalTab">
      <ul>
        <li :class="[$route.path == item.linkTo?'active':'']" v-for="item in userItem">
          <router-link :to="item.linkTo">{{item.text}}</router-link>
        </li>
      </ul>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'personalCenter',
    data () {
      return {
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
    mounted: function () {
//      console.log(this.$route)
    }
  }
</script>

<style>
  #personalCenter {
    padding: 40px 50px;
  }

  #personalCenter .personalTab {
    height: 60px;
    background: #fff;
    box-shadow: 0 10px 20px rgba(44, 65, 103, 0.07);
  }

  #personalCenter .personalTab ul {
    padding-top: 20px;
  }

  #personalCenter .personalTab ul li {
    float: left;
    width: 160px;
    text-align: center;
    border-right: 2px solid #dcdcdc;
  }

  #personalCenter .personalTab ul li.active a {
    color: #03afc5;
    font-weight: bold;
  }

  #personalCenter .personalTab ul li a {
    display: block;
    color: #6e818f;
    font-size: 14px;
  }


</style>

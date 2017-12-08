<template>
  <div id="acountForm">
    <form action="">
      <div class="formItem">
        <p><i class="iconfont icon-mingpian"></i>账号</p>
        <input type="text" v-model="accountInfo.acountNum">
      </div>
      <div class="formItem">
        <p><i class="iconfont icon-mingpian"></i>昵称</p>
        <input type="text">
      </div>
      <div class="formItem">
        <p><i class="iconfont icon-mingpian"></i>状态</p>
        <input type="text">
      </div>
      <div class="formItem">
        <p><i class="iconfont icon-mingpian"></i>创建时间</p>
        <DatePicker type="datetime" placeholder="Select date" style="width: 100%"></DatePicker>
      </div>
      <div class="formItem">
        <p><i class="iconfont icon-mingpian"></i>更新时间</p>
        <DatePicker type="datetime" placeholder="Select date" style="width: 100%"></DatePicker>
      </div>
      <div class="formItem">
        <p><i class="iconfont icon-mingpian"></i>账号</p>
        <input type="text">
      </div>
    </form>
  </div>
</template>

<script>
  export default {
    name: 'acountForm',
    data: function () {
      return {
        accountInfo: {
          acountNum: '727245543'
        }
      }
    }
  }
</script>

<style>
  #acountForm {
    width: 55%;
    float: left;
  }

  #acountForm .formItem {
    width: 40%;
    margin-right: 20%;
    float: left;
    margin-top: 20px;
  }

  #acountForm .formItem:nth-of-type(2n) {
    margin-right: 0;
  }

  #acountForm .formItem input {
    width: 100%;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    text-indent: 15px;
    border-radius: 0;
  }
  #acountForm .formItem p{
    color: #3d8fc7;
    font-size: 14px;
    margin-bottom: 5px;
    letter-spacing: 2px;
  }
  #acountForm .formItem p i{
    color: #78909c;
    margin-right: 5px;
  }
</style>

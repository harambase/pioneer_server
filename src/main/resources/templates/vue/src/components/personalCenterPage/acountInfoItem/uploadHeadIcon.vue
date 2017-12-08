<template>
  <div id="uploadHeadIcon">
    <div class="headIcon">
      <img src="" alt="">
    </div>
    <div class="btnWrap">
      <Upload action="//jsonplaceholder.typicode.com/posts/">
        <Button class="uploadBtn blue" type="ghost">上传新头像</Button>
      </Upload>
    </div>
    <div v-if="!hasApplicationId" class="btnWrap">
      <button @click="applyApplicationId()" class="uploadBtn green">申请应用ID</button>
    </div>
    <div v-else="" class="btnWrap">
      <span class="borderRadiusSpace">应用ID:{{applicationId}}</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'uploadHeadIcon',
    data: function () {
      return {
        applicationId: '84854552',
        hasApplicationId: false
      }
    },
    methods: {
      applyApplicationId () {
        this.hasApplicationId = !this.hasApplicationId
      }
    }
  }
</script>

<style>
  #uploadHeadIcon {
    width: 40%;
    height: 252px;
    float: left;
    padding-top: 50px;
  }

  #uploadHeadIcon .headIcon {
    width: 80px;
    height: 80px;
    border-radius: 40px;
    margin: 0 auto;
    border: 3px solid #b8c7c8;
  }

  #uploadHeadIcon .btnWrap {
    text-align: center;
    margin-top: 8px;
  }

  #uploadHeadIcon .btnWrap .uploadBtn {
    width: 140px;
    height: 30px;
    border-radius: 15px;
    border: none;
    color: #fff;
    cursor: pointer;
    outline: none;
  }

  #uploadHeadIcon .btnWrap .uploadBtn.blue {
    background: #03afc5;
  }

  #uploadHeadIcon .btnWrap .uploadBtn.green {
    background: #28d582;
  }

  #uploadHeadIcon .btnWrap .uploadBtn:hover {
    color: #fff;
  }

  #uploadHeadIcon .btnWrap .borderRadiusSpace {
    display: inline-block;
    width: 140px;
    height: 30px;
    line-height: 27px;
    border-radius: 15px;
    border: 1px solid #3de1b0;
    color: #3de1b0;
  }
</style>

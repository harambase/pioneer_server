<template>
  <div id="accountInfo">
    <div class="clearfix accountTop">
      <acountForm></acountForm>
      <uploadHeadIcon></uploadHeadIcon>
    </div>
    <accountSpace></accountSpace>
    <div class="footBtn">
      <button class="sureBtn" @click="closeUserdetail">取消</button>
      <button class="cancelBtn">确定</button>
    </div>
  </div>
</template>

<script>
  import acountForm from './acountInfoItem/acountForm.vue'
  import uploadHeadIcon from './acountInfoItem/uploadHeadIcon.vue'
  import accountSpace from './acountInfoItem/accountSpace.vue'
  export default {
    name: 'accountInfo',
    components: {
      acountForm,
      uploadHeadIcon,
      accountSpace
    },
    methods: {
      closeUserdetail () {
        console.log('asd')
        this.$emit('closedetail')
      }
    }
  }
</script>

<style>
  #accountInfo {
    margin-top: 20px;
    padding-top: 40px;
    padding-bottom: 30px;
    padding-left: 9%;
    background: #fff;
    height: 665px;
  }
  #accountInfo .accountTop{
    height: 260px;
  }
  #accountInfo .footBtn{
    text-align: right;
    padding-right: 100px;
  }
  #accountInfo .footBtn button{
    margin-left: 70px;
  }
  .sureBtn{
    border: none;
    outline: none;
    width: 100px;
    height: 35px;
    background: #bdd0de;
    color: #fff;
    cursor: pointer;
  }
  .cancelBtn{
    border: none;
    outline: none;
    width: 100px;
    height: 35px;
    background: #4193cf;
    color: #fff;
    cursor: pointer;
  }
</style>

<template>
  <div id="localStorageEdite" v-show="showEdit">
    <div class="userCenterDetailWrap">
      <div class="firstStep">
        <h3 class="userCenterDetailWrapTitle">编辑 <span @click="closeLocalStorageEdite" class="iconfont icon-guanbi"></span>
        </h3>
        <form action="" class="localStorageEditeForm clearfix">
          <div class="formItem">
            <p><i class="iconfont icon-mingpian"></i>名称</p>
            <input type="text">
          </div>
          <div class="formItem">
            <p><i class="iconfont icon-mingpian"></i>权限</p>
            <input type="text">
          </div>
          <div class="formItem">
            <p><i class="iconfont icon-mingpian"></i>分类</p>
            <input type="text">
          </div>
          <div class="formItem">
            <p><i class="iconfont icon-mingpian"></i>性别</p>
            <RadioGroup>
              <Radio label="男"></Radio>
              <Radio label="女"></Radio>
              <Radio label="保密"></Radio>
            </RadioGroup>
          </div>
          <div class="formItem">
            <p><i class="iconfont icon-mingpian"></i>出生年月日</p>
            <DatePicker type="date" placeholder="Select date"
                        style="width: 100%"></DatePicker>
          </div>
          <div class="formItem">
            <p><i class="iconfont icon-mingpian"></i>出生地</p>
            <Cascader></Cascader>
          </div>
          <div class="textareaWrap">
            <p><i class="iconfont icon-mingpian"></i>关键字</p>
            <textarea></textarea>
          </div>
          <div class="textareaWrap">
            <p><i class="iconfont icon-mingpian"></i>个人描述</p>
            <textarea></textarea>
          </div>
        </form>
        <div class="footBtn">
          <button class="sureBtn">取消</button>
          <button class="cancelBtn">下一步</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'localStorageEdite',
    props: [
      'showEdit'
    ],
    data () {
      return {}
    },
    methods: {
      closeLocalStorageEdite () {
        this.$emit('showLocalStorageEdite', false)
      }
    }

  }
</script>

<style>
  #localStorageEdite {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(55, 55, 55, .6);
  }

  #localStorageEdite .userCenterDetailWrap {
    width: 1200px;
    height: 850px;
    margin: 20px auto;
    background: #fff;
    box-shadow: 0 2px 1px rgba(44, 65, 103, 0.12);
  }

  #localStorageEdite form.localStorageEditeForm {
    width: 850px;
    margin: 50px auto;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep {
    width: 100%;
    height: 100%;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .formItem {
    width: 25%;
    margin-right: 12.5%;
    float: left;
    margin-top: 20px;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .formItem:nth-of-type(3n) {
    margin-right: 0;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .formItem input {
    width: 100%;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    text-indent: 15px;
    border-radius: 0;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .formItem p {
    color: #3d8fc7;
    font-size: 14px;
    margin-bottom: 5px;
    letter-spacing: 2px;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .formItem p i {
    color: #78909c;
    margin-right: 5px;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .textareaWrap {
    float: left;
    width: 100%;
    margin-top: 20px;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .textareaWrap p {
    color: #3d8fc7;
    font-size: 14px;
    margin-bottom: 5px;
    letter-spacing: 2px;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .textareaWrap textarea {
    width: 100%;
    height: 150px;
    resize: none;
    border: 1px solid rgba(185, 197, 201, 0.6);
    background: #f6fbff;
    outline: none;
  }

  #localStorageEdite .userCenterDetailWrap .firstStep .textareaWrap p i {
    color: #78909c;
    margin-right: 5px;
  }

  #localStorageEdite .userCenterDetailWrap .footBtn {
    text-align: right;
    padding-right: 175px;
  }

  #localStorageEdite .userCenterDetailWrap .footBtn button {
    margin-left: 70px;
  }

  .sureBtn {
    border: none;
    outline: none;
    width: 100px;
    height: 35px;
    background: #bdd0de;
    color: #fff;
    cursor: pointer;
  }

  .cancelBtn {
    border: none;
    outline: none;
    width: 100px;
    height: 35px;
    background: #4193cf;
    color: #fff;
    cursor: pointer;
  }
</style>

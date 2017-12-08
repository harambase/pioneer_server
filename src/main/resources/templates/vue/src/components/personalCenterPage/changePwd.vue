<template>
  <div id="changePwd">
    <Form class="changePwdForm" ref="formCustom" :model="formCustom" :rules="ruleCustom" action="">
      <FormItem class="formItem" prop="oldpwd">
        <p><i class="iconfont icon-mingpian"></i>原密码</p>
        <Input type="password" v-model="formCustom.oldpwd"></Input>
      </FormItem>
      <FormItem class="formItem" prop="passwd">
        <p><i class="iconfont icon-mingpian"></i>新密码</p>
        <Input type="password" v-model="formCustom.passwd"></Input>
      </FormItem>
      <FormItem class="formItem" prop="passwdCheck">
        <p><i class="iconfont icon-mingpian"></i>确认密码</p>
        <Input type="password" v-model="formCustom.passwdCheck"></Input>
      </FormItem>
    </form>
    <div class="footBtn">
      <button class="sureBtn">取消</button>
      <button class="cancelBtn">确定</button>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'changePwd',
    data () {
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码'))
        } else {
          if (this.formCustom.passwdCheck !== '') {
            // 对第二个密码框单独验证
            this.$refs.formCustom.validateField('passwdCheck')
          }
          callback()
        }
      }
      const validatePassCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入新密码'))
        } else if (value !== this.formCustom.passwd) {
          callback(new Error('两次密码不一致!'))
        } else {
          callback()
        }
      }
      return {
        formCustom: {
          passwd: '',
          passwdCheck: '',
          oldpwd: ''
        },
        ruleCustom: {
          passwd: [
            {validator: validatePass, trigger: 'blur'}
          ],
          passwdCheck: [
            {validator: validatePassCheck, trigger: 'blur'}
          ],
          oldpwd: [
            {required: true, message: '请输入原密码', trigger: 'blur'}
          ]
        }
      }
    }
  }
</script>

<style>
  #changePwd {
    margin-top: 20px;
    height: 665px;
    background: #fff;
    padding-top: 120px;
  }

  #changePwd .changePwdForm {
    width: 300px;
    margin: 0 auto;
  }

  #changePwd .changePwdForm .formItem {
    width: 100%;
    margin-top: 20px;
  }

  #changePwd .changePwdForm .formItem input {
    width: 100%;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    text-indent: 15px;
    border-radius: 0;
  }

  #changePwd .changePwdForm .formItem p {
    color: #3d8fc7;
    font-size: 14px;
    margin-bottom: 5px;
    letter-spacing: 2px;
  }

  #changePwd .changePwdForm .formItem p i {
    color: #78909c;
    margin-right: 5px;
  }

  #changePwd .footBtn {
    text-align: right;
    margin-top: 120px;
    padding-right: 210px;
  }

  #changePwd .footBtn button {
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

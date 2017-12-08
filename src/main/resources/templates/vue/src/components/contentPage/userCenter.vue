<template>
  <div id="userCenter">
    <div class="filterWrap">
      <div class="search">
        <input type="text" placeholder="搜索关键字">
        <i class="iconfont icon-chakan"></i>
      </div>
      <div class="btnWrap">
        <button @click="deleteList()" title="删除">
          <i class="iconfont icon-chakan"></i>
        </button>
        <button title="新增" @click="addNewUserModal=true">
          <i class="iconfont icon-chakan"></i>
        </button>
      </div>
      <div class="selectWrap">
        <select v-model="showUserNumber">
          <option v-for="item in showUserNumberList">{{item.val}}</option>
        </select>
      </div>
    </div>
    <table class="userTable">
      <thead>
      <tr>
        <th>
          <input v-model="isCheckedAll" @click="selectAll" type="checkbox">全选
        </th>
        <th>接收人</th>
        <th>类型</th>
        <th>标题</th>
        <th>内容</th>
        <th>发送人</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,index) in userList">
        <td><input type="checkbox" v-model="item.isChecked"></td>
        <td>{{item.receiveMan}}</td>
        <td>{{item.type}}</td>
        <td>{{item.title}}</td>
        <td>{{item.content}}</td>
        <td>{{item.sendMan}}</td>
        <td>{{item.status}}</td>
        <td>{{item.createTime}}</td>
        <td>
          <div class="btnWrap">
            <button @click="userdetail=true" title="详情"><i class="iconfont icon-chakan"></i></button>
            <button title="编辑"><i class="iconfont icon-chakan"></i></button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="tablePage">
      <Page :total="100" show-total show-elevator></Page>
    </div>
    <userCenterDetail :userdetail="userdetail" v-on:showdetail="detailFromChild"></userCenterDetail>
    <!--添加新用户-->
    <Modal
      v-model="addNewUserModal"
      title="添加新用户"
      @on-ok="addUser"
    >
      <Form class="changePwdForm" ref="addUserInfo" :model="addUserInfo" :rules="ruleCustom" action="">
        <FormItem class="formItem" prop="accountNumber">
          <p><i class="iconfont icon-mingpian"></i>账号</p>
          <Input type="text" v-model="addUserInfo.accountNumber"></Input>
        </FormItem>
        <FormItem class="formItem" prop="newPasswd">
          <p><i class="iconfont icon-mingpian"></i>密码</p>
          <Input type="password" v-model="addUserInfo.newPasswd"></Input>
        </FormItem>
        <FormItem class="formItem" prop="idnumber">
          <p><i class="iconfont icon-mingpian"></i>ID</p>
          <Input type="text" v-model="addUserInfo.idnumber"></Input>
        </FormItem>
      </form>
    </Modal>
  </div>
</template>
<script>
  import userCenterDetail from './userCenterDetail/userCenterDetail.vue'
  export default {
    name: 'userCenter',
    components: {
      userCenterDetail
    },
    data: function () {
      return {
        addUserInfo: {
          accountNumber: '',
          newPasswd: '',
          idnumber: ''
        },
        ruleCustom: {
          accountNumber: [
            {required: true, message: '请输入账号', trigger: 'blur'}
          ],
          newPasswd: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
          idnumber: [
            {required: true, message: '请输入ID', trigger: 'blur'}
          ]
        },
        addNewUserModal: false,
        isCheckedAll: false,
        showUserNumber: '10',
        showUserNumberList: [
          {
            val: '5'
          },
          {
            val: '10'
          },
          {
            val: '15'
          }
        ],
        userList: [
          {
            isChecked: false,
            receiveMan: '张全但',
            type: 'text',
            title: 'keyword',
            content: 'asdasd',
            sendMan: 'Tom',
            status: '上线',
            createTime: '2017-09-22 12:45:00'
          },
          {
            isChecked: true,
            receiveMan: '张全但1',
            type: 'text',
            title: 'keyword',
            content: 'asdasd',
            sendMan: 'Tom',
            status: '上线',
            createTime: '2017-09-22 12:45:00'
          }
        ],
        userdetail: false
      }
    },
    methods: {
      selectAll (event) {
        if (event.currentTarget.checked) {
          this.userList.forEach(function (item, i) {
            item.isChecked = true
          })
        } else {
          this.userList.forEach(function (item, i) {
            item.isChecked = false
          })
        }
      },
      deleteList () {
        this.userList = this.userList.filter(function (item, i) {
          return !item.isChecked
        })
      },
      addUser () {

      },
      detailFromChild (msg) {
        console.log(msg)
        this.userdetail = msg
      }
    },
    watch: {
      userList: {
        handler: function (val, oldVal) {
          let length = 0
          this.userList.forEach(function (item, i) {
            if (item.isChecked === true) {
              length++
            }
          })
          if (this.userList.length === length) {
            this.isCheckedAll = true
          } else {
            this.isCheckedAll = false
          }
        },
        // 深度观察
        deep: true
      }
    }
  }
</script>

<style>
  #userCenter {
    padding: 40px 50px;
  }

  #userCenter .filterWrap {
    width: 100%;
    height: 60px;
    background: #fff;
  }

  #userCenter .filterWrap .search {
    position: relative;
    width: 280px;
    float: left;
    margin-top: 13px;
    margin-left: 20px;
    border-right: 2px solid #dcdcdc;
  }

  #userCenter .filterWrap .search input {
    width: 240px;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    border-radius: 0;
    text-indent: 28px;
  }

  #userCenter .filterWrap .search i.iconfont {
    position: absolute;
    top: 5px;
    left: 5px;
    color: #dcdcdc;
  }

  #userCenter .filterWrap .btnWrap {
    float: left;
    margin-top: 13px;
  }

  #userCenter .filterWrap .btnWrap button {
    background: #eceff1;
    width: 35px;
    height: 35px;
    border: none;
    outline: none;
    margin-left: 20px;
  }

  #userCenter .filterWrap .selectWrap {
    float: right;
    margin-top: 13px;
    margin-right: 30px;
  }

  #userCenter .filterWrap .selectWrap select {
    width: 240px;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    border-radius: 0;
    outline: none;
  }

  #userCenter table.userTable {
    margin-top: 20px;
    width: 100%;
    background: #fff;
    border-spacing: 0;
    box-shadow: 0 1px 20px rgba(4, 0, 0, 0.1);
  }

  #userCenter table.userTable thead {
    color: #78909c;
    font-size: 14px;
    background: #f6fbff;
  }

  #userCenter table.userTable thead th {
    height: 60px;
    font-weight: normal;
    border-bottom: 1px solid #eee;
  }

  #userCenter table.userTable tbody tr {
    border-bottom: 1px solid #eee;
  }

  #userCenter table.userTable tbody tr:hover {
    box-shadow: 0 0 1px 2px rgba(0, 175, 196, 0.5);
  }

  #userCenter table.userTable tbody tr:hover td {
    color: #03afc5 !important;
  }

  #userCenter table.userTable tbody tr td {
    height: 70px;
    text-align: center;
    color: #cdcdcd;
    border-bottom: 1px solid #eee;
  }

  #userCenter table.userTable tbody tr td div.btnWrap {
    border-left: 2px solid #e6ebf8;
  }

  #userCenter table.userTable tbody tr td div.btnWrap button {
    background: #eceff1;
    width: 30px;
    height: 30px;
    border: none;
    outline: none;
    margin-left: 10px;
    cursor: pointer;
  }

  #userCenter table.userTable tbody tr td:nth-of-type(2) {
    color: #3d8fc7;
  }

  #userCenter table.userTable tbody tr td:nth-of-type(4), #userCenter table.userTable tbody tr td:nth-of-type(6) {
    color: #6e818f;
  }

  #userCenter .tablePage {
    margin-top: 20px;
    text-align: right;
  }
</style>

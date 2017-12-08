<template>
  <div id="centerStorage">
    <div class="filterWrap">
      <div class="search">
        <input type="text" placeholder="搜索关键字">
        <i class="iconfont icon-chakan"></i>
      </div>
      <div class="btnWrap">
        <button @click="deleteList()" title="删除">
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
            <button title="上线"><i class="iconfont icon-chakan"></i></button>
            <button @click="activeApiModal = true" title="激活"><i class="iconfont icon-chakan"></i></button>
            <button title="详情"><i class="iconfont icon-chakan"></i></button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="tablePage">
      <Page :total="100" show-total show-elevator></Page>
    </div>
    <!--激活api-->
    <Modal
      v-model="activeApiModal"
      title="激活API"
      width="400"
      @on-ok="activeApiModalOk"
      @on-cancel="activeApiModalCancel">
      <div class="activeApiModalWrap">
        <p class="textLabel">请设置失效时间</p>
        <p>
          <DatePicker type="date" placeholder="Select date" style="width: 200px"></DatePicker>
        </p>
      </div>
    </Modal>
  </div>
</template>
<script>
  export default {
    name: 'centerStorage',
    data: function () {
      return {
        activeApiModal: false,
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
        ]
      }
    },
    methods: {
      activeApiModalOk () {

      },
      activeApiModalCancel () {

      },
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
  #centerStorage {
    padding: 40px 50px;
  }
  .activeApiModalWrap{
    width: 200px;
    margin: 0 auto;
  }
  .activeApiModalWrap p.textLabel{
    margin-bottom: 5px;
    color: #006ad9;
  }

  #centerStorage .filterWrap {
    width: 100%;
    height: 60px;
    background: #fff;
  }

  #centerStorage .filterWrap .search {
    position: relative;
    width: 280px;
    float: left;
    margin-top: 13px;
    margin-left: 20px;
    border-right: 2px solid #dcdcdc;
  }

  #centerStorage .filterWrap .search input {
    width: 240px;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    border-radius: 0;
    text-indent: 28px;
  }

  #centerStorage .filterWrap .search i.iconfont {
    position: absolute;
    top: 5px;
    left: 5px;
    color: #dcdcdc;
  }

  #centerStorage .filterWrap .btnWrap {
    float: left;
    margin-top: 13px;
  }

  #centerStorage .filterWrap .btnWrap button {
    background: #eceff1;
    width: 35px;
    height: 35px;
    border: none;
    outline: none;
    margin-left: 20px;
  }

  #centerStorage .filterWrap .selectWrap {
    float: right;
    margin-top: 13px;
    margin-right: 30px;
  }

  #centerStorage .filterWrap .selectWrap select {
    width: 240px;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    border-radius: 0;
    outline: none;
  }

  #centerStorage table.userTable {
    margin-top: 20px;
    width: 100%;
    background: #fff;
    border-spacing: 0;
    box-shadow: 0 1px 20px rgba(4, 0, 0, 0.1);
  }

  #centerStorage table.userTable thead {
    color: #78909c;
    font-size: 14px;
    background: #f6fbff;
  }

  #centerStorage table.userTable thead th {
    height: 60px;
    font-weight: normal;
    border-bottom: 1px solid #eee;
  }

  #centerStorage table.userTable tbody tr {
    border-bottom: 1px solid #eee;
  }

  #centerStorage table.userTable tbody tr:hover {
    box-shadow: 0 0 1px 2px rgba(0, 175, 196, 0.5);
  }

  #centerStorage table.userTable tbody tr:hover td {
    color: #03afc5 !important;
  }

  #centerStorage table.userTable tbody tr td {
    height: 70px;
    text-align: center;
    color: #cdcdcd;
    border-bottom: 1px solid #eee;
  }

  #centerStorage table.userTable tbody tr td div.btnWrap {
    border-left: 2px solid #e6ebf8;
  }

  #centerStorage table.userTable tbody tr td div.btnWrap button {
    background: #eceff1;
    width: 30px;
    height: 30px;
    border: none;
    outline: none;
    margin-left: 10px;
    cursor: pointer;
  }

  #centerStorage table.userTable tbody tr td:nth-of-type(2) {
    color: #3d8fc7;
  }

  #centerStorage table.userTable tbody tr td:nth-of-type(4), #centerStorage table.userTable tbody tr td:nth-of-type(6) {
    color: #6e818f;
  }

  #centerStorage .tablePage {
    margin-top: 20px;
    text-align: right;
  }
</style>


<template>
  <div id="messageCenter">
    <div class="filterWrap">
      <div class="search">
        <input type="text" placeholder="搜索关键字">
        <i class="iconfont icon-chakan"></i>
      </div>
      <div class="btnWrap">
        <button @click="deleteList()" title="删除">
          <i class="iconfont icon-chakan"></i>
        </button>
        <button title="新增">
          <i class="iconfont icon-chakan"></i>
        </button>
      </div>
      <div class="selectWrap">
        <select v-model="showMessageNumber">
          <option v-for="item in showMessageNumberList">{{item.val}}</option>
        </select>
      </div>
    </div>
    <table class="messageTable">
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
      <tr v-for="(item,index) in messageList">
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
            <button @click="deleteAlert(index)" title="忽略"><i class="iconfont icon-chakan"></i></button>
            <button title="快速处理"><i class="iconfont icon-chakan"></i></button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="tablePage">
      <Page :total="100" show-total show-elevator></Page>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'messageCenter',
    data: function () {
      return {
        isCheckedAll: false,
        messageList: [
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
        showMessageNumber: '10',
        showMessageNumberList: [
          {
            val: '5'
          },
          {
            val: '10'
          },
          {
            val: '15'
          }
        ]
      }
    },
    methods: {
      selectAll (event) {
        if (event.currentTarget.checked) {
          this.messageList.forEach(function (item, i) {
            item.isChecked = true
          })
        } else {
          this.messageList.forEach(function (item, i) {
            item.isChecked = false
          })
        }
      },
      deleteAlert (index) {
        this.$Modal.confirm({
          title: '提示',
          content: `<div class="modal-bd">
                        <p>是否决定忽略此条信息？</p>
                     </div>`,
          loading: true,
          onOk: () => {
            this.messageList.splice(index, 1)
            setTimeout(() => {
              this.$Modal.remove()
            }, 2000)
          },
          onCancel: () => {

          }
        })
      },
      deleteList () {
        this.messageList = this.messageList.filter(function (item, i) {
          return !item.isChecked
        })
      }
    },
    watch: {
      messageList: {
        handler: function (val, oldVal) {
          let length = 0
          this.messageList.forEach(function (item, i) {
            if (item.isChecked === true) {
              length++
            }
          })
          if (this.messageList.length === length) {
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
  #messageCenter {
    padding: 40px 50px;
  }

  #messageCenter .filterWrap {
    width: 100%;
    height: 60px;
    background: #fff;
  }

  #messageCenter .filterWrap .search {
    position: relative;
    width: 280px;
    float: left;
    margin-top: 13px;
    margin-left: 20px;
    border-right: 2px solid #dcdcdc;
  }

  #messageCenter .filterWrap .search input {
    width: 240px;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    border-radius: 0;
    text-indent: 28px;
  }

  #messageCenter .filterWrap .search i.iconfont {
    position: absolute;
    top: 5px;
    left: 5px;
    color: #dcdcdc;
  }

  #messageCenter .filterWrap .btnWrap {
    float: left;
    margin-top: 13px;
  }

  #messageCenter .filterWrap .btnWrap button {
    background: #eceff1;
    width: 35px;
    height: 35px;
    border: none;
    outline: none;
    margin-left: 20px;
  }

  #messageCenter .filterWrap .selectWrap {
    float: right;
    margin-top: 13px;
    margin-right: 30px;
  }

  #messageCenter .filterWrap .selectWrap select {
    width: 240px;
    height: 35px;
    background: #f6fbff;
    border: 1px solid rgba(185, 197, 201, 0.6);
    color: #6e818f;
    border-radius: 0;
    outline: none;
  }

  #messageCenter table.messageTable {
    margin-top: 20px;
    width: 100%;
    background: #fff;
    border-spacing: 0;
    box-shadow: 0 1px 20px rgba(4, 0, 0, 0.1);
  }

  #messageCenter table.messageTable thead {
    color: #78909c;
    font-size: 14px;
    background: #f6fbff;
  }

  #messageCenter table.messageTable thead th {
    height: 60px;
    font-weight: normal;
    border-bottom: 1px solid #eee;
  }

  #messageCenter table.messageTable tbody tr {
    border-bottom: 1px solid #eee;
  }

  #messageCenter table.messageTable tbody tr:hover {
    box-shadow: 0 0 1px 2px rgba(0, 175, 196, 0.5);
  }

  #messageCenter table.messageTable tbody tr:hover td {
    color: #03afc5 !important;
  }

  #messageCenter table.messageTable tbody tr td {
    height: 70px;
    text-align: center;
    color: #cdcdcd;
    border-bottom: 1px solid #eee;
  }

  #messageCenter table.messageTable tbody tr td div.btnWrap {
    border-left: 2px solid #e6ebf8;
  }

  #messageCenter table.messageTable tbody tr td div.btnWrap button {
    background: #eceff1;
    width: 30px;
    height: 30px;
    border: none;
    outline: none;
    margin-left: 10px;
    cursor: pointer;
  }

  #messageCenter table.messageTable tbody tr td:nth-of-type(2) {
    color: #3d8fc7;
  }

  #messageCenter table.messageTable tbody tr td:nth-of-type(4), #messageCenter table.messageTable tbody tr td:nth-of-type(6) {
    color: #6e818f;
  }

  #messageCenter .tablePage {
    margin-top: 20px;
    text-align: right;
  }
</style>

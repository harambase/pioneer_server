<template>
  <div id="accountSpace">
    <div class="leftChart">
      左边图表
    </div>
    <div class="rightProgress">
      <div class="item clearfix" v-for="item in progressList">
        <label class="progressName">{{item.name}}</label>
        <div class="progressBar">
          <Progress :status="item.status" :percent="item.percent" hide-info></Progress>
        </div>
        <div class="progressNumber">已用<span>{{item.space}}MB</span></div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'accountSpace',
    data: function () {
      return {
        progressList: [
          {
            name: 'MYSQL',
            percent: 45,
            space: '4558',
            status: 'normal'
          },
          {
            name: 'HDFS',
            percent: 65,
            space: '4873',
            status: 'wrong'
          },
          {
            name: 'NFS',
            percent: 30,
            space: '7458',
            status: 'success'
          }
        ]
      }
    }
  }
</script>

<style>
  #accountSpace {
    width: 100%;
    height: 300px;
  }

  #accountSpace .leftChart {
    width: 40%;
    float: left;
  }

  #accountSpace .rightProgress {
    width: 60%;
    float: right;
    margin-top: 40px;
  }

  #accountSpace .rightProgress > .item {
    margin-top: 40px;
    width: 100%;
  }

  #accountSpace .rightProgress > .item > .progressName {
    width: 12%;
    text-align: left;
    float: left;
    color: #cecece;
    font-size: 18px;
  }

  #accountSpace .rightProgress > .item > .progressBar {
    width: 61%;
    float: left;
  }

  #accountSpace .rightProgress > .item > .progressNumber {
    width: 27%;
    text-align: left;
    float: left;
    color: #cecece;
    font-size: 16px;
    padding-left: 15px;
  }

  #accountSpace .rightProgress > .item > .progressNumber > span {
    color: #3d8fc7;
  }
</style>

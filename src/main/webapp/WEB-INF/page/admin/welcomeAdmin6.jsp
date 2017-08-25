<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 2017/8/7
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${basePath}/static/css/pages/title.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/static/css/welcomeAdmin.css" rel="stylesheet" type="text/css">
    <title>Dashboard</title>
</head>
<body>
<%@include file="common/welcomeHeader.jsp"%>
<div class="site-branding-text">
    <h1 class="site-title">Administrator Manage Site</h1>
    <p class="site-description">Dashboard</p>
    <div class="choose-view" >
        <div class='w_manage_btn system-control-btn'>
            <button id="user" class="w_button" style="float:left; margin-left: 10px">User Chart</button>
        </div>
        <div class='w_manage_btn system-control-btn'>
            <button id="connection" class="w_button" style="margin-left: 10px">Relation Chart</button>
        </div>
    </div>
    <hr/>
</div>


<div id="chart">
    <div class="exampleChart" style="margin-top:25px; margin-left:300px;width: 800px;height: 500px;">
        <div id="exampleChart" style="width: 800px;height: 500px;"></div>
    </div>
</div> <!-- /account-container -->
<div id="relation">
    <div class="exampleChart" style="margin-top:25px; margin-left:300px;width: 1000px;height: 700px;">
        <div id="relationChart" style="width: 1000px;height: 700px;"></div>
    </div>
</div> <!-- /account-container -->

</body>
<script src="${basePath}/static/plugins/echarts/echarts.min.js"></script>
<script src="${basePath}/static/js/otherJs/chart.js"></script>
<script src="${basePath}/static/js/otherJs/dataTool.js"></script>
<script src="${basePath}/static/js/adminJs/welcomeAdmin.js"></script>
</html>

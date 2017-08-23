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
    <p class="site-description">Welcome back!</p>
    <hr/>
</div>


<div class="account-container register">
    <div class="content clearfix">
        <div class="exampleChart" style="margin-top:25px; width: 800px;height: 500px;">
            <div id="exampleChart" style="width: 800px;height: 500px;"></div>
        </div>
    </div> <!-- /content -->
    <div class="content clearfix">
        <div class="exampleChart" style="margin-top:25px; width: 800px;height: 500px;">
            <div id="relationChart" style="width: 800px;height: 500px;"></div>
        </div>
    </div> <!-- /content -->
</div> <!-- /account-container -->

</body>
<script src="${basePath}/static/plugins/echarts/echarts.min.js"></script>
<script src="${basePath}/static/js/otherJs/chart.js"></script>
<script src="${basePath}/static/js/otherJs/dataTool.js"></script>
<script src="${basePath}/static/js/adminJs/welcomeAdmin.js"></script>
</html>

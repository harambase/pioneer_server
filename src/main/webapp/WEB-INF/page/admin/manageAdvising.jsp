<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>辅导关系管理</title>
    <%@include file="../common/commonJs.jsp" %>
    <%@include file="../common/commonCSS.jsp" %>
    <%@include file="common/adminCSS.jsp" %>
    <link href="${basePath}/static/css/header.css" rel="stylesheet">
    <link href="${basePath}/static/css/manageTranscript.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="common/manageHeader.jsp" %>

<div class="site-branding-text">
    <h1 class="site-title">Administrator Manage Site</h1>
    <p class="site-description">Advising Management</p>
    <div class="choose-view" >
        <table>
            <tr>
                <td>
                    <div class='w_manage_btn system-control-btn'>
                        <button id="overview" class="w_button" style="margin-left: 10px">总览</button>
                    </div>
                </td>
                <td>
                    <div class='w_manage_btn system-control-btn'>
                        <button id="class" class="w_button" style="margin-left: 10px">按教师浏览</button>
                    </div>
                </td>
                <td>
                    <div class='w_manage_btn system-control-btn'>
                        <button id="user" class="w_button" style="margin-left: 10px">按学生浏览</button>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="user-pop">
    <div class="w_wrap">
        <div class="w_head">
            <span>选择学生</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body" style="margin-top: 21px;">
            <div class="content clearfix" style="padding: 0px">
                <h4 class="type">学生列表</h4>
                <hr style="margin: 7px 0 7px 0;"/>
                <table width="100%;" style="font-size: 14px;" id="userTable" class="display dataTable">
                </table>
            </div> <!-- /content -->
        </div>
    </div>
</div>
<div class="class-pop">
    <div class="w_wrap">
        <div class="w_head">
            <span>选择教师</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body" style="margin-top: 21px;">
            <div class="content clearfix" style="padding: 0px">
                <h4 class="type">教师列表</h4>
                <hr style="margin: 7px 0 7px 0;"/>
                <table width="100%;" style="font-size: 14px;" id="classTable" class="display dataTable">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="w_wrapper">
    <div class="w_wrap">
        <div class="w_head">
            <span>修改辅导关系</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body">
            <ul class="w_tab clearfix">
                <li class="active base-info">修改辅导关系</li>
            </ul>
            <div class="w_tabC w_pop">
                <div class='w_manage'>
                    <div id="aF">
                        <button class="w_button" style="width: 190px;height: 35px; vertical-align: middle;" id="assignF">分配新的老师</button>
                        <div id="assignFDiv" class="group-form-input">
                            <input id="searchFValue2" placeholder="Assign Faculty" style="margin-right: 20px; height: 30px;float: left; width: 155px;" required>
                            <span class="w_button w_add" id="addf-button2" style="width: 145px; text-align: center;">修改老师</span>
                            <span class="w_button w_add" id="cancelF" style="width: 100px; text-align: center; background-color: #3d8ca7;">取消</span>
                            <ul class="w_selected3">
                            </ul>
                        </div>
                    </div>
                    <div id="aS">
                        <button class="w_button" style="width: 190px;height: 35px; vertical-align: middle;" id="assignC">分配新的学生</button>
                        <div id="assignCDiv" class="group-form-input">
                            <input id="searchCValue2" placeholder="Assign Student" style="margin-right: 20px; height: 30px;float: left; width: 155px;">
                            <span class="w_button w_add" id="addc-button2" style="width: 145px; text-align: center;">修改学生</span>
                            <span class="w_button w_add" id="cancelC" style="width: 100px; text-align: center; background-color: #3d8ca7;">取消</span>
                            <ul class="w_selected4">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class='w_manage_btn system-control-btn'>
                    <button id="cancel2" class="w_button">退出</button>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="view">
    <div class="spilt register-table" style = "padding-left: 300px">
        <div style=";margin: 0 120px 0 0;width: 1000px;height: 700px;" class="account-container register">
            <div class="content clearfix">
                <h1 id="h1" style="margin-left: -19px;margin-top: 15px;">辅导关系总览表</h1>
                <div class='course-table' >
                    <table width="100%" style="font-size: 14px;" id="transTable" class="display dataTable">
                    </table>
                </div>
            </div> <!-- /content -->
        </div> <!-- /account-container -->
    </div>
</div>

</body>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/adminJs/manageAdvise.js"></script>

</html>

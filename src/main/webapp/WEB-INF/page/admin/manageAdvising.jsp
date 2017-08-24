<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>
<%@include file="common/adminCSS.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Advising Management</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link href="${basePath}/static/css/manageTranscript.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${basePath}/static/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css">

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
                        <button id="overview" class="w_button" style="margin-left: 10px">Overview</button>
                    </div>
                </td>
                <td>
                    <div class='w_manage_btn system-control-btn'>
                        <button id="class" class="w_button" style="margin-left: 10px">Faculty View</button>
                    </div>
                </td>
                <td>
                    <div class='w_manage_btn system-control-btn'>
                        <button id="user" class="w_button" style="margin-left: 10px">Student View</button>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <hr/>
</div>
<div class="user-pop">
    <div class="user-pop-inner">
        <div class="w_head">
            <span>User Selection</span>
            <span class="w_close">×</span>
        </div>
        <ul class="w_tab clearfix">
            <li id="student" class="active">Students</li>
        </ul>
        <h1 id="user-title">Lists of Students in System</h1>
        <div class='user-table' style="width: 756px;margin: -45px 0px 27px 37px;" >
            <table width="100%;" style="font-size: 14px;" id="userTable" class="display dataTable">
            </table>
        </div>
    </div>
</div>
<div class="class-pop">
    <div class="user-pop-inner">
        <div class="w_head">
            <span>Faculty Selection</span>
            <span class="w_close">×</span>
        </div>
        <ul class="w_tab clearfix">
            <li class="active">Faculty</li>
        </ul>
        <div class='class-table' style="width: 756px;margin: 0px 0px 27px 37px;" >
            <table width="100%;" style="font-size: 14px;" id="classTable" class="display dataTable">
            </table>
        </div>
    </div>
</div>
<div class="w_wrapper">
    <div class="w_wrap">
        <div class="w_head">
            <span>Edit Advisor</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body">
            <ul class="w_tab clearfix">
                <li class="active base-info">Advising Relation Change</li>
            </ul>
            <div class="w_tabC w_pop">
                <div class='w_manage'>
                    <div id="aF">
                        <button class="w_button" style="width: 190px;height: 35px; vertical-align: middle;" id="assignF">Assigned New Faculty</button>
                        <div id="assignFDiv" class="group-form-input">
                            <input id="searchFValue2" placeholder="Assign Faculty" style="margin-right: 20px; height: 30px;float: left; width: 155px;" required>
                            <span class="w_button w_add" id="addf-button2" style="width: 145px; text-align: center;">Change Faculty</span>
                            <span class="w_button w_add" id="cancelF" style="width: 100px; text-align: center; background-color: #3d8ca7;">Cancel</span>
                            <ul class="w_selected3">
                            </ul>
                        </div>
                    </div>
                    <div id="aS">
                        <button class="w_button" style="width: 190px;height: 35px; vertical-align: middle;" id="assignC">Assigned New Student</button>
                        <div id="assignCDiv" class="group-form-input">
                            <input id="searchCValue2" placeholder="Assign Student" style="margin-right: 20px; height: 30px;float: left; width: 155px;">
                            <span class="w_button w_add" id="addc-button2" style="width: 145px; text-align: center;">Change Student</span>
                            <span class="w_button w_add" id="cancelC" style="width: 100px; text-align: center; background-color: #3d8ca7;">Cancel</span>
                            <ul class="w_selected4">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class='w_manage_btn system-control-btn'>
                    <button id="cancel2" class="w_button">Exit</button>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="view">
    <div class="spilt register-table" style = "padding-left: 300px">
        <div style=";margin: 0 120px 0 0;width: 1500px;height: 700px;" class="account-container register">
            <div class="content clearfix">
                <h1 id="h1" style="margin-left: -19px;margin-top: 15px;">General Advising List</h1>
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

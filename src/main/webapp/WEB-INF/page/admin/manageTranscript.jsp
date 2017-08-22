<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>
<%@include file="common/adminCSS.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Transcript Management</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <link href="${basePath}/static/css/manageTranscript.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="common/manageHeader.jsp" %>

<div class="site-branding-text">
    <h1 class="site-title">Administrator Manage Site</h1>
    <p class="site-description">Transcript Management</p>
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
                    <button id="class" class="w_button" style="margin-left: 10px">Class View</button>
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
            <span>Course Selection</span>
            <span class="w_close">×</span>
        </div>
        <ul class="w_tab clearfix">
            <li class="active">Course</li>
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
            <span>Edit Transcript</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body">
            <ul class="w_tab clearfix">
                <li class="active base-info">Grade Change</li>
            </ul>

            <div class="w_tabC w_pop">
                <div class="w_content">
                    <div class="w_basicInfo account-container register" style="margin-left: 0px;">
                        <form id="editTranscript" method="post" onsubmit="return false">
                            <table style="margin: 15px 27px;">
                                <tr>
                                    <td><p style="float: left">Student ID:</td>
                                    <td><input id="sid" name="sid" value="" class="login" disabled/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">CRN:</td>
                                    <td><input id="crn" name="crn" value="" class="login" disabled/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Credits:</p></td>
                                    <td><input id="credits" name="credits" value="" class="login" disabled/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Grade:</p></td>
                                    <td> <input id="grade" name="grade" value="" class="login" minlength="1" required/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Completion:</p></td>
                                    <td> <input id="complete" type="radio" value="Complete"/>Complete
                                         <input id="process" type="radio" value="In Progress"/>In Progress
                                         <input id="nComplete" type="radio" value="Not Complete"/>Not Complete</td>
                                </tr>
                            </table>
                        </form>
                    </div> <!-- /account-container -->
                </div>
                <div class="w_buttons_w system-control-btn">
                    <button id="confirm" class="w_button">Edit</button>
                    <button id="cancel" class="w_button">Exit</button>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="view">
    <div class="spilt register-table" style = "padding-left: 300px">
        <div style=";margin: 0 120px 0 0;width: 1500px;height: 700px;" class="account-container register">
            <div class="student-only">
                <h1 id="h2" style="margin-left: 17px;"></h1>
                <div class="student-trans">
                    <table id="student-table" style="margin-left: 100px;color:#555;font-size: 15px;line-height: 1.2;">
                    </table>
                </div>
                <div class="class-trans">
                    <table id="class-table"></table>
                </div>
                <hr style="margin-bottom: -5px;"/>
            </div> <!-- /content -->

            <div class="content clearfix">
                <h1 id="h1" style="margin-left: -19px;margin-top: 15px;">All Transcripts</h1>
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
<script src="${basePath}/static/js/adminJs/manageTranscript.js"></script>
</html>

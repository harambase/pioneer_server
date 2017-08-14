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
        <div class='w_manage_btn system-control-btn'>
            <button id="class" class="w_button" style="float: left">Class View</button>
        </div>
        <div class='w_manage_btn system-control-btn'>
            <button id="user" class="w_button" style="margin-left: 10px">User View</button>
        </div>
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
            <li id="student" class="active">Student</li>
        </ul>
        <h1 id="user-title">Lists of Users in System</h1>
        <div class='user-table' style="width: 756px;margin: 0px 0px 27px 37px;" >
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

<div class="user-view">
    <div class="spilt register-table" style = "padding-left: 300px">
        <div style=";margin: 0 120px 0 0;width: 1500px;height: 700px;" class="account-container register">
            <div class="content clearfix">
                <h1>List of Registered Course</h1>
                <div class='course-table' >
                    <table width="100%" style="font-size: 14px;" id="transTable" class="display dataTable">
                    </table>
                </div>
            </div> <!-- /content -->
        </div> <!-- /account-container -->
    </div>
</div>
<div class="class-view">
    <div class="spilt register-table">
        <div style="float: left; height: 652px" class="account-container register">
            <div class="content clearfix">
                <form id="createCourseForm" method="post" onsubmit="return false">
                    <h1>Create a course</h1>
                    <div class="login-fields">

                        <div class="field">
                            <label for="year-semester">Year-semester:</label>
                            <input id="year-semester" name="year-semester" value="" placeholder="year-semester:YYYY-SS" class="login"
                                   minlength="7" maxlength="7" required/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="name">Course Name:</label>
                            <input id="name" name="name" value="" placeholder="Course Name" class="login"
                                   minlength="1" maxlength="20" required/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="credits">Last Name:</label>
                            <input id="credits" name="credits" value="" placeholder="Credits" class="login"
                                   minlength="1" maxlength="2" required/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="coulev">Course Level:</label>
                            <input id="coulev" name="coulev" value="" placeholder="Course Level" class="login" maxlength="3"/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="cousec">Section:</label>
                            <input id="cousec" name="cousec" value="" placeholder="Course Section" class="login" maxlength="2"/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="startdate">Start Date:</label>
                            <input id="startdate" name="startdate" value="" placeholder="Start Date: YYYY-MM-DD"
                                   class="login" minlength="10" maxlength="10"/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="enddate">End Date:</label>
                            <input id="enddate" name="enddate" value="" placeholder="End Date: YYYY-MM-DD"
                                   class="login" minlength="10" maxlength="10"/>
                        </div> <!-- /field -->
                        <div class="field">
                            <label for="starttime">Start Time:</label>
                            <input id="starttime" name="starttime" value="" placeholder="Start Time: HH:MM:SS"
                                   class="login" minlength="8" maxlength="8"/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="endtime">End Time:</label>
                            <input id="endtime" name="endtime" value="" placeholder="End Time: HH:MM:SS"
                                   class="login" minlength="8" maxlength="8"/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="capa">Capacity:</label>
                            <input id="capa" name="capa" value="" placeholder="Capacity" class="login"/>
                        </div> <!-- /field -->
                        <div class="field">
                            <div class="group-form-input">
                                <label for="searchFValue">Assign Teacher:</label>
                                <input id="searchFValue" placeholder="Assign Faculty" style="float: left; width: 155px;" required>
                                <i id="searchFButton" class='fa fa-search fa-lg'></i>
                                <span class="w_button w_add" id="addf-button">Check Faculty</span>
                                <ul class="w_selected1">
                                </ul>
                            </div>
                            <div class="group-form-input">
                                <label for="searchCValue">Assign Prerequest Course:</label>
                                <input id="searchCValue" placeholder="Assign Precourse" style="float: left; width: 155px;">
                                <i id="searchCButton" class='fa fa-search fa-lg'></i>
                                <span class="w_button w_add" id="addc-button">Check Precourse</span>
                                <ul class="w_selected2">
                                </ul>
                            </div>

                        </div> <!-- /field -->
                    </div> <!-- /login-fields -->
                    <div class='w_day'>
                        <h4 class="status" style="margin-top: 0px;">Choose Day: </h4>
                        <input class='m' type="checkbox" name="day" value="m"
                               style="margin: 10px 4px 10px 0; width: 12px;"/>Mon
                        <input class='t' type="checkbox" name="day" value="t"
                               style="margin: 10px 4px 10px 0; width: 12px;"/>Tue
                        <input class='w' type="checkbox" name="day" value="w"
                               style="margin: 10px 4px 10px 0; width: 12px;"/>Wed
                        <input class='tr' type="checkbox" name="day" value="tr"
                               style="margin: 10px 4px 10px 0; width: 12px;"/>Thr
                        <input class='f' type="checkbox" name="day" value="f"
                               style="margin: 10px 4px 10px 0; width: 12px;"/>Fri
                        <input class='sa' type="checkbox" name="day" value="sa"
                               style="margin: 10px 4px 10px 0; width: 12px;"/>Sat
                        <input class='s' type="checkbox" name="day" value="s"
                               style="margin: 10px 4px 10px 0; width: 12px;"/>Sun
                    </div>

                    <div class="login-actions" style="margin-top: -15px;">

                    <span class="login-checkbox">
                        <input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
                        <label class="choice" for="Field">Agree with the Terms & Conditions.</label>
                    </span>
                        <button class="button btn btn-primary btn-large" id="registerBtn">Create</button>

                    </div> <!-- .actions -->
                </form>
            </div> <!-- /content -->
        </div> <!-- /account-container -->

        <div style="float: right;margin: 0 120px 0 0;width: 1065px;height: 652px;"
             class="account-container register">
            <div class="content clearfix">
                <h1>Lists of Courses in System</h1>
                <div class='course-table'>
                    <table width="100%" style="font-size: 14px;" id="courseTable" class="display dataTable">
                    </table>
                </div>
            </div> <!-- /content -->
        </div> <!-- /account-container -->
    </div>
</div>


</body>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/manageTranscript.js"></script>
</html>

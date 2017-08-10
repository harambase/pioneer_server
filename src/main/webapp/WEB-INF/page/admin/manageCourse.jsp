<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Course Management</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <link href="${basePath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}/static/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

    <link href="${basePath}/static/css/font-awesome.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">

    <link href="${basePath}/static/css/style.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/static/css/pages/signin.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${basePath}/static/css/header.css" media="screen" type="text/css" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="${basePath}/static/css/pages/title.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/static/css/manageUser.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="manageHeader.jsp" %>
<div class="site-branding-text">
    <h1 class="site-title">Administrator Manage Site</h1>
    <p class="site-description">Course Management</p>
    <hr/>
</div>

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

<div class="w_wrapper">
    <div class="w_wrap">
        <div class="w_head">
            <span>Account Setting</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body">
            <ul class="w_tab clearfix">
                <li class="active base-info">Profile</li>
                <li class='account'>Account Setting</li>
            </ul>
            <div class="w_tabC w_pop">
                <div class="w_content">
                    <div class="w_basicInfo account-container register" style="margin-left: 0px;">
                        <form id="editUserForm" method="post" onsubmit="return false">
                            <table style="margin: 15px 27px;">
                                <input id="pwd" name="pwd" value="" class="login" hidden/>
                                <tr>
                                    <td><p style="float: left">UserID:</td>
                                    <td><input id="userid2" name="userid" value="" class="login" disabled/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Username:</td>
                                    <td><input id="username2" name="username" value="" class="login" disabled/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">First Name:</p></td>
                                    <td><input id="firstname2" name="firstname" value=""class="login" minlength="1" maxlength="20" required/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Last Name:</p></td>
                                    <td><input id="lastname2" name="lastname" value="" class="login"
                                               minlength="1" maxlength="20" required/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Birthday:</p></td>
                                    <td><input id="birthday2" name="birthday" value="YYYY-MM-DD" class="login" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Email:</p></td>
                                    <td> <input id="email2" name="email" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">QQ:</p></td>
                                    <td><input id="qq2" name="QQ" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">weChat:</p></td>
                                    <td><input id="weChat2" name="weChat" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Mobile:</p></td>
                                    <td><input id="tel2" name="tel" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">Dormitory:</p></td>
                                    <td><input id="dorm2" name="dorm" value="" class="login"/></td>
                                </tr>
                            </table>
                        </form>
                    </div> <!-- /account-container -->
                </div>
                <p class="w_buttons_w system-control-btn">
                    <button id="confirm" class="w_button">Confirm</button>
                    <button id="cancel" class="w_button">Cancel</button>
                </p>
            </div>
            <div class="w_tabD w_pop">
                <div class='w_manage'>
                    <h4 class="status" style="margin-top: 0px;">Account Status: </h4>
                    <input class='enable' type="checkbox" name="powerState" value="1"/>ACTIVE
                    <input class='disable' type="checkbox" name="powerState" value="0"/>DEACTIVE

                    <h4 class="type">Account Type: </h4>
                    <input class='student' type="checkbox" name="powerState" value="s"/>STUDENT
                    <input class='teacher' type="checkbox" name="powerState" value="f"/>FACULTY
                    <input class='admin' type="checkbox" name="powerState" value="a"/>ADMINISTRATOR

                    <h4 class="gender">User Gender:</h4>
                    <input class='male' type="checkbox" name="powerState" value="male"/>MALE
                    <input class='female' type="checkbox" name="powerState" value="female"/>FEMALE

                    <h4 class='reset-pass w_hide'>Reset Password：</h4>
                    <p class='w_hide'>重置用户账号，新密码将通过邮箱发送给用户邮箱。</p>
                    <button class='w_hide' id="reset">Reset Password</button>
                </div>
                <div class='w_manage_btn system-control-btn'>
                    <button id="apply" class="w_button">Confirm</button>
                    <button id="cancel2" class="w_button">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/manageCourse.js"></script>
</html>

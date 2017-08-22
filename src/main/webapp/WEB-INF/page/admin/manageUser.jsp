<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>
<%@include file="common/adminCSS.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>User Management</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">

</head>
<body>
<%@include file="common/manageHeader.jsp" %>
<div class="site-branding-text">
    <h1 class="site-title">Administrator Manage Site</h1>
    <p class="site-description">User Management</p>
    <hr/>
</div>

<div class="spilt register-table">
    <div style="float: left; height: 586px" class="account-container register">
        <div class="content clearfix">
            <form id="createUserForm" method="post" onsubmit="return false">
                <h1>Create a new account</h1>
                <div class="login-fields">

                    <div class="field">
                        <label for="year-semester">Year-semester:</label>
                        <input id="year-semester" name="year-semester" value="-" placeholder="year-semester" class="login"
                               minlength="7" maxlength="7" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="firstname">First Name:</label>
                        <input id="firstname" name="firstname" value="" placeholder="First Name" class="login"
                               minlength="1" maxlength="20" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="lastname">Last Name:</label>
                        <input id="lastname" name="lastname" value="" placeholder="Last Name" class="login"
                               minlength="1" maxlength="20" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="birthday">Birthday:</label>
                        <input id="birthday" name="birthday" value="YYYY-MM-DD" placeholder="birthday" class="login"
                               maxlength="10"/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="email">Email Address:</label>
                        <input id="email" name="email" value="" placeholder="Email" class="login"/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="qq">QQ:</label>
                        <input id="qq" name="QQ" value="" placeholder="QQ" class="login"/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="weChat">weChat:</label>
                        <input id="weChat" name="weChat" value="" placeholder="weChat" class="login"/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="tel">tel:</label>
                        <input id="tel" name="tel" value="" placeholder="tel" class="login"/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="dorm">dorm:</label>
                        <input id="dorm" name="dorm" value="" placeholder="dorm" class="login"/>
                    </div> <!-- /field -->
                    <div class="field" >
                        <p style="float: left;margin-bottom: 4.5px;margin-top: 4.5px;">Type:</p>
                        <select name="type" id="type" title="Type" style="width: 261px;margin-left: 5px;">
                            <option id="student" value="s">Student</option>
                            <option id="faculty" value="f">Faculty</option>
                            <option id="admin"   value="a">Administrator</option>
                        </select>
                    </div> <!-- /field -->
                    <div class="field" >
                        <p style="float: left;margin-bottom: 4.5px;">Gender:</p>
                        <select name="gender" id="gender" title="Type" style="width: 245px;margin-left: 5px;">
                            <option id="male" value="male">Male</option>
                            <option id="female" value="female">Female</option>
                        </select>
                    </div> <!-- /field -->

                </div> <!-- /login-fields -->

                <div class="login-actions" style="margin-top: -30px;">

                    <span class="login-checkbox">
                        <input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
                        <label class="choice" for="Field">Agree with the Terms & Conditions.</label>
                    </span>
                    <button class="button btn btn-primary btn-large" id="registerBtn">Register</button>

                </div> <!-- .actions -->
            </form>
        </div> <!-- /content -->
    </div> <!-- /account-container -->

    <div style="float: right;margin: 0px 120px 0 0;width: 1065px;height: 586px;"
         class="account-container register">
    <div class="content clearfix">
        <h1>Lists of Users in System</h1>
        <div class='user-table'>
            <table width="100%" style="font-size: 14px;" id="userTable" class="display dataTable">
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
                    <button class='w_hide' id="resetPassword">Reset</button>
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
<script src="${basePath}/static/js/adminJs/manageUser.js"></script>
</html>

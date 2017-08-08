manageCourse.jsp<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>User Management</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <link href="../../../static/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../../../static/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

    <link href="../../../static/css/font-awesome.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">

    <link href="../../../static/css/style.css" rel="stylesheet" type="text/css">
    <link href="../../../static/css/pages/signin.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../../../static/css/header.css" media="screen" type="text/css" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="../../../static/css/pages/title.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="manageHeader.jsp" %>
<div class="site-branding-text">
    <h1 class="site-title">Administrator Manage Site</h1>
    <p class="site-description">User Management</p>
    <hr/>
</div>
<div class="account-container register">
    <div class="content clearfix">
        <form id="createUserForm" method="post">
            <h1>Create a new account</h1>
            <div class="login-fields">

                <div class="field">
                    <label for="year-semester">First Name:</label>
                    <input type="text" id="year-semester" name="year-semester" value="" placeholder="year-semester" class="login" />
                </div> <!-- /field -->

                <div class="field">
                    <label for="firstname">First Name:</label>
                    <input type="text" id="firstname" name="firstname" value="" placeholder="First Name" class="login" />
                </div> <!-- /field -->

                <div class="field">
                    <label for="lastname">Last Name:</label>
                    <input type="text" id="lastname" name="lastname" value="" placeholder="Last Name" class="login" />
                </div> <!-- /field -->

                <div class="field">
                    <label for="email">Email Address:</label>
                    <input type="text" id="email" name="email" value="" placeholder="Email" class="login"/>
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

            <div class="login-actions">

				<span class="login-checkbox">
					<input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
					<label class="choice" for="Field">Agree with the Terms & Conditions.</label>
				</span>
                <button class="button btn btn-primary btn-large" id="registerBtn">Register</button>

            </div> <!-- .actions -->
        </form>
    </div> <!-- /content -->
</div> <!-- /account-container -->

<%--<div class="account-container register">--%>
    <%--<div class="content clearfix">--%>
        <%--<div class='user-table'>--%>
            <%--<table id="userTable" class="table table-striped table-hover table-checkable order-column tableDiy">--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</div> <!-- /content -->--%>
<%--</div> <!-- /account-container -->--%>

</body>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/manageUser.js"></script>
</html>

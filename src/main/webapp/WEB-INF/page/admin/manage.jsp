<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Signup - Bootstrap Admin Template</title>

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

</head>
<body>
<div class="sidebar" style="top: 0px;">
    <h1><i class="fa fa-bars push"></i>Administrator <span class="color">Manage</span></h1>
    <ul>
        <li><a href="${basePath}/welcomeAdmin"><i class="fa fa-dashboard push"></i>Dashboard<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="#"><i class="fa fa-user push"></i>Course Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="#"><i class="fa fa-file push"></i>Course Lookup<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="#"><i class="fa fa-cog push"></i>User Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="#"><i class="fa fa-plane push"></i>Advising Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="#"><i class="fa fa-plane push"></i>Transcript Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
    </ul>
</div>
<script src="../../../static/js/otherJs/index.js"></script>


<div class="account-container register">
    <div class="content clearfix">
        <form action="#" method="post">
            <h1>Signup for Free Account</h1>
            <div class="login-fields">
                <p>Create your free account:</p>
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
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="" placeholder="Password" class="login"/>
                </div> <!-- /field -->

                <div class="field">
                    <label for="confirm_password">Confirm Password:</label>
                    <input type="password" id="confirm_password" name="confirm_password" value="" placeholder="Confirm Password" class="login"/>
                </div> <!-- /field -->

            </div> <!-- /login-fields -->

            <div class="login-actions">

				<span class="login-checkbox">
					<input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
					<label class="choice" for="Field">Agree with the Terms & Conditions.</label>
				</span>
                <button class="button btn btn-primary btn-large">Register</button>

            </div> <!-- .actions -->
        </form>
    </div> <!-- /content -->
</div> <!-- /account-container -->


<%--<!-- Text Under Box -->--%>
<%--<div class="login-extra">--%>
    <%--Already have an account? <a href="login.html">Login to your account</a>--%>
<%--</div> <!-- /login-extra -->--%>

</body>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
</html>

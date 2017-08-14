manageCourse.jsp<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

</head>
<body>
<%@include file="common/manageHeader.jsp" %>

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

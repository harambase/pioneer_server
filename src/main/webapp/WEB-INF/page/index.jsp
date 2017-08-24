<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/commonInner.jsp" %>
<!DOCTYPE html>
<html lang="en-US" class="gr__sts_csbsju_edu">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10.000">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <title>Sign In</title>
    <link rel="stylesheet" href="${basePath}/static/css/login.css">

</head>
<body class="body" data-gr-c-s-loaded="true">
<div id="fullPage">
    <div id="brandingWrapper" class="float">
        <div id="branding" class="illustrationClass"></div>
    </div>
    <div id="contentWrapper" class="float">
        <div id="content">
            <div id="header">
                <img src="${basePath}/Images/HaramBaseICON.png" style="width: 400px;">
            </div>
            <div id="workArea">
                <div id="authArea" class="groupMargin">
                    <div id="loginArea">
                        <div id="loginMessage" class="groupMargin">Log in using your Pioneer network account:</div>

                        <form method="post" id="loginForm">
                            <div id="formsAuthenticationArea">
                                <div id="userNameArea">
                                    <input id="username" name="UserName" value="" minlength="1"
                                           class="text fullWidth" spellcheck="false" placeholder="Username">
                                </div>
                                <div id="passwordArea">
                                    <input id="password" name="Password" type="password" minlength="1"
                                           class="text fullWidth" placeholder="Password">
                                </div>
                                <div id="submissionArea" class="submitMargin">
                                    <span id="btnLogin" class="submit" tabindex="4">Sign in</span>
                                </div>
                            </div>
                            <input id="optionForms" type="hidden" name="AuthMethod" value="FormsAuthentication">
                        </form>

                        <div id="introduction" class="groupMargin">
                            <p><b><font color="red">NOTE:</font> Use your Pioneer provided username when signing in.</b></p>
                        </div>
                    </div>
                </div>
            </div>
            <div id="footerPlaceholder">
                <b><%@include file="common/commonFooter.jsp" %></b>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${basePath}/static/plugins/confirm/confirm.js"></script>
<script src="${basePath}/static/plugins/jQuery/jquery-1.11.3.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/login.js"></script>
</html>
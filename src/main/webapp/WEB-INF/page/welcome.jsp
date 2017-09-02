<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/commonInner.jsp" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10.000">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <title>登录</title>
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
                        <div id="loginMessage" class="groupMargin">进入:</div>
                        <form>
                            <div id="adSys"><input id="admin"   type="submit" class="button" value="管理系统"/></div>
                            <div id="faSys"><input id="faculty" type="submit" class="button" value="教学系统"/></div>
                            <div id="stSys"><input id="student" type="submit" class="button" value="学生系统"/></div>
                        </form>
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
<script src="${basePath}/static/plugins/jQuery/jquery-1.11.3.js"></script>
<script src="${basePath}/static/plugins/jQuery/jquerySession.js"></script>
<script src="${basePath}/static/plugins/confirm/confirm.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/login.js"></script>
</html>
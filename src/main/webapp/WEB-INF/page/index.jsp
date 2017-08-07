<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../index.css">
    <title>登录</title>
</head>
<%@include file="common/commonInner.jsp" %>
<%--<%@include file="common/commonHeader.jsp"%>--%>
<body id="cas">
<div id="header">
    <div class="logo">
        <img src="Images/HaramBaseICON.png">
    </div>
</div>
<div id="outerWrapper">
    <div id="innerWrapper">
        <h1>Welcome to Pioneer Self Service</h1>
        <div id="content">
            <div id="input">
                <form id="fm1" onsubmit="return false">
                    <div id="usernamefield">
                        <label for="username">Username</label>
                        <input id="username" name="uname" class="userpass" value="" size="25" autocomplete="off" required>
                    </div>
                    <div id="passwordfield">
                        <label for="password">Password</label>
                        <input id="password" name="password" class="userpass" type="password" value="" size="25"
                               autocomplete="off" required minlength="6" maxlength="15">
                    </div>
                    <input name="submit" id="btnLogin" type="submit" class="btn" value="Login">
                    <input type="reset" id="btnReset" class="btn" value="Reset">
                </form>
                <a href="${basePath}/welcomeStudent">消息中心</a>
            </div>
        </div>
    </div>
</div>


<%@include file="common/commonFooter.jsp"%>
<%@include file="common/commonJs.jsp" %>

<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/login.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/commonInner.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>登录</title>

  <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900italic,900&subset=latin,greek,greek-ext,vietnamese,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>

  <link rel="icon" type="image/png" sizes="16x16" href="${basePath}/static/assets/img/favicon-16x16.png">
  <link rel="icon" type="image/png" sizes="32x32" href="${basePath}/static/assets/img/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="96x96" href="${basePath}/static/assets/img/favicon-96x96.png">

  <link rel="stylesheet" href="${basePath}/static/styles/vendor-dddc8eebb5.css">

  <link rel="stylesheet" href="${basePath}/static/styles/auth-6732706e87.css">
</head>
<body>
<main class="auth-main">
  <div class="auth-block">
    <h1>Sign in to Pioneer</h1>
    <p class="auth-link"><b><font color="red">注意：</font> 请使用用户名登录(不是ID).</b></p>

    <form id="loginForm" class="form-horizontal" onsubmit="return false">
      <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名</label>

        <div class="col-sm-10">
          <input class="specialCharValidate form-control" id="username" minlength="1" stringCheck="true" required placeholder="用户名">
        </div>
      </div>
      <div class="form-group">
        <label for="password" class="col-sm-2 control-label" minlength="6" maxlength="16" required>密码</label>

        <div class="col-sm-10">
          <input type="password" class="specialCharValidate form-control" id="password" placeholder="密码">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button id = "btnLogin" class="btn btn-default btn-auth">登录</button>
          <a href class="forgot-pass">忘记密码?</a>
        </div>
      </div>
    </form>
    <a href="${basePath}/reg" class="auth-link">还未注册先锋账号？立即申请!</a>
  </div>
</main>
</body>
<script src="${basePath}/static/plugins/jQuery/jquery-1.11.3.js"></script>
<script src="${basePath}/static/plugins/jQuery/jquerySession.js"></script>
<script src="${basePath}/static/plugins/confirm/confirm.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/login.js"></script>
<script>
    // 字符验证，只能包含中文、英文、数字、下划线等字符。
    jQuery.validator.addMethod("stringCheck", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);
    }, "只能包含中文、英文、数字、下划线等字符");
</script>
</html>
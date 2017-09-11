<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/commonInner.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>注册</title>

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
    <h1>申请加入先锋</h1>
    <a href="${basePath}/auth" class="auth-link">已有先锋账号？点击登录!</a>

    <form class="form-horizontal">
      <div class="form-group">
        <label for="inputName3" class="col-sm-2 control-label">姓名</label>

        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputName3" stringCheck="true placeholder="*全名" minlength="1" maxlength="20" required>
        </div>
      </div>
      <div class="form-group">
        <label for="inputEmail3" class="col-sm-2 control-label">邮箱</label>

        <div class="col-sm-10">
          <input type="email" class="form-control" id="inputEmail3" placeholder="*邮件">
        </div>
      </div>
      <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">密码</label>

        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputPassword3" placeholder="*密码" minlength="6" maxlength="16" required>
        </div>
      </div>
      <div class="form-group">
        <label for="inputName3" class="col-sm-2 control-label">QQ</label>

        <div class="col-sm-10">
          <input type="text" class="form-control" id="qq" isDigits="true" placeholder="*QQ号" minlength="6" maxlength="16" required>
        </div>
      </div>
      <div class="form-group">
        <label for="inputName3" class="col-sm-2 control-label">电话号码</label>

        <div class="col-sm-10">
          <input type="text" class="form-control" id="tel" isDigits="true" placeholder="*电话号码" minlength="8" maxlength="11" required>
        </div>
      </div>
      <div class="form-group">
        <label for="inputName3" class="col-sm-2 control-label">生日</label>

        <div class="col-sm-10">
          <input type="text" class="form-control" id="birthday" placeholder="*生日">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default btn-auth">提交申请</button>
        </div>
      </div>
    </form>
  </div>

</main>
<script src="${basePath}/static/plugins/jQuery/jquery-1.11.3.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/plugins/layer/laydate/laydate.js"></script>
<script>
    laydate.render({
        elem: '#birthday',
        theme: '#393D49',showBottom: false//指定元素
    });
    // 字符验证，只能包含中文、英文、数字、下划线等字符。
    jQuery.validator.addMethod("stringCheck", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);
    }, "只能包含中文、英文、数字、下划线等字符");
    // 只能输入[0-9]数字
    jQuery.validator.addMethod("isDigits", function(value, element) {
        return this.optional(element) || /^\d+$/.test(value);
    }, "只能输入0-9数字");
</script>
</body>

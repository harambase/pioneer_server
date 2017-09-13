<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 2017/8/7
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="utf-8">
    <title>消息中心</title>
    <%@include file="../common/commonJs.jsp" %>
    <%@include file="../common/commonCSS.jsp" %>
    <%@include file="../common/adminCSS.jsp" %>
    <link rel="stylesheet" href="${basePath}/static/css/header.css">
    <link rel="stylesheet" href="${basePath}/static/css/manageCourse.css">
</head>
<body>
<%@include file="../common/manageHeader.jsp" %>
<div class="spilt register-table">
    <div id="add-div" style="position:absolute;width: 1563px;height: 700px;display: none" class="account-container register">
        <div class="content clearfix">
            <form id="createCourseForm" method="post" class="createCourseForm" onsubmit="return false">
                <h1>写一个信息</h1>
                <div class="login-fields">
                    <h4>基本信息</h4><hr/>
                    <table>
                        <tr>
                            <td><p style="float: left">*年份-学期：</p></td><td><input id="year-semester" placeholder="YYYY-SS" name="year-semester" value="" class="login" minlength="7" maxlength="7" required/></td>
                            <td><p style="float: left">*课程名：</p></td><td><input id="name" name="name" value="" class="login" minlength="1" maxlength="20" required/></td>
                            <td><p style="float: left">*课程等级：</p></td><td><input id="coulev" name="coulev" value="" placeholder="*100-499" class="login" maxlength="3" required/></td>
                            <td><p style="float: left">*课程班级：</p></td><td><input id="cousec" name="cousec" value="" placeholder="*01,02,03..." class="login" maxlength="2" required/></td>

                        </tr>
                        <tr>
                            <td><p style="float: left">*学分:</p></td><td><input id="credits" name="credits" value="" class="login" minlength="1" maxlength="1" required/></td>
                            <td><p style="float: left">*课程容量:</p></td><td><input id="capa" name="capa" value="" placeholder="*课程容量" class="login" required/></td>
                            <td><p style="float: left">教室:</p></td><td><input id="classroom" name="classroom" value="" placeholder="教室" class="login" maxlength="20"/></td>
                        </tr>
                    </table>
                    <h4>时间信息</h4><hr/>
                    <table>
                        <tr>
                            <td><p style="float: left">*开始日期：</p></td><td><input id="startdate" name="startdate" value="" class="login" minlength="10" maxlength="10" required/></td>
                            <td><p style="float: left">*结束日期：</p></td><td><input id="enddate" name="enddate" value="" class="login" minlength="10" maxlength="10" required/></td>
                            <td><p style="float: left">*开始时间：</p></td><td><input id="starttime" name="starttime" value="" class="login" minlength="8" maxlength="8" required/></td>
                            <td><p style="float: left">*结束时间：</p></td><td><input id="endtime" name="endtime" value="" class="login" minlength="8" maxlength="8" required/></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td><p class="status">*选择上课的时间: </p></td>
                            <td><div class='w_day'>
                                <input class='m' type="checkbox" name="day" value="m"
                                       style="height: 14px;margin: 10px 4px 10px 0; width: 13px;"/>星期一
                                <input class='t' type="checkbox" name="day" value="t"
                                       style="height: 14px;margin: 10px 4px 10px 0; width: 13px;"/>星期二
                                <input class='w' type="checkbox" name="day" value="w"
                                       style="height: 14px;margin: 10px 4px 10px 0; width: 13px;"/>星期三
                                <input class='tr' type="checkbox" name="day" value="tr"
                                       style="height: 14px;margin: 10px 4px 10px 0; width: 13px;"/>星期四
                                <input class='f' type="checkbox" name="day" value="f"
                                       style="height: 14px;margin: 10px 4px 10px 0; width: 13px;"/>星期五
                                <input class='sa' type="checkbox" name="day" value="sa"
                                       style="height: 14px;margin: 10px 4px 10px 0; width: 13px;"/>星期六
                                <input class='s' type="checkbox" name="day" value="s"
                                       style="height: 14px;margin: 10px 4px 10px 0; width: 13px;"/>星期日
                            </div></td>
                        </tr>
                    </table>
                    <h4>教师信息</h4><hr/>
                    <table>
                        <tr>
                            <td><p>*分配教师:</p></td>
                            <td><div class="group-form-input">
                                <input id="searchFValue" required>
                                <ul class="w_selected1">
                                </ul>
                            </div></td>
                            <td><p>分配预选课程(非必填)：</p></td>
                            <td><div class="group-form-input">
                                <input id="searchCValue">
                                <ul class="w_selected2">
                                </ul>
                            </div></td>
                        </tr>
                    </table>
                </div> <!-- /login-fields -->

                <hr style="margin-top: 5px"/>
                <div class="login-actions" style="margin-top: -15px;">
                    <textarea id="comment" style="margin:5px 0px 0px 0px; width: 300px; height: 100px; resize: none;" placeholder="请输入备注信息"></textarea>
                    <span class="login-checkbox">
                        <input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
                        <label class="choice" for="Field">确认上述信息正确无误</label>
                    </span>
                    <button class="button btn btn-primary btn-large" id="registerBtn">创建</button>
                </div>
            </form>
        </div> <!-- /content -->

    </div> <!-- /account-container -->
    <div id="course-div" style="float: right;position:absolute;width: 1563px;height: 700px; display: block"
         class="account-container register">
        <div class="content clearfix">
            <h1>消息列表</h1>
            <div class='course-table'>
                <table width="100%" style="font-size: 14px;" id="messageTable" class="display dataTable">
                </table>
            </div>
        </div> <!-- /content -->
    </div> <!-- /account-container -->
</div>
</body>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/adminJs/manageCourse.js"></script>
<script src="${basePath}/static/plugins/layui/layui.all.js"></script>
</html>

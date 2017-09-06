<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>课程管理</title>
    <%@include file="../common/commonJs.jsp" %>
    <%@include file="../common/commonCSS.jsp" %>
    <%@include file="common/adminCSS.jsp" %>
    <link rel="stylesheet" href="${basePath}/static/css/header.css">
</head>
<body>
<%@include file="common/manageHeader.jsp" %>
<div class="site-branding-text">
    <h1 class="site-title">Administrator Manage Site</h1>
    <p class="site-description">Course Management</p>
    <div class="choose-view" >
        <div class='w_manage_btn system-control-btn'>
            <button id="add" class="w_button" style="float:left; margin-left: 10px">添加一个课程</button>
        </div>
        <div class='w_manage_btn system-control-btn'>
            <button id="list" class="w_button" style="margin-left: 10px">浏览课程列表</button>
        </div>
    </div>

</div>
<div class="spilt register-table">
    <div id="add-div" style="position:absolute;height: 870px; display: none" class="account-container register">
        <div class="content clearfix">
            <form id="createCourseForm" method="post" onsubmit="return false">
                <h1>添加一个课程</h1>
                <div class="login-fields">

                    <div class="field">
                        <label for="year-semester">Year-semester:</label>
                        <input id="year-semester" name="year-semester" value="" placeholder="*年份-学期(YYYY-SS)" class="login"
                               minlength="7" maxlength="7" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="name">Course Name:</label>
                        <input id="name" name="name" value="" placeholder="*课程名" class="login"
                               minlength="1" maxlength="20" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="credits">Last Name:</label>
                        <input id="credits" name="credits" value="" placeholder="*学分" class="login"
                               minlength="1" maxlength="2" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="coulev">Course Level:</label>
                        <input id="coulev" name="coulev" value="" placeholder="*课程等级：100-499" class="login" maxlength="3" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="cousec">Section:</label>
                        <input id="cousec" name="cousec" value="" placeholder="*课程班级(01,02,03...)" class="login" maxlength="2" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="classroom">Section:</label>
                        <input id="classroom" name="classroom" value="" placeholder="教室" class="login" maxlength="20"/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="startdate">Start Date:</label>
                        <input id="startdate" name="startdate" value="" placeholder="*开始日期：YYYY-MM-DD" class="login" minlength="10" maxlength="10" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="enddate">End Date:</label>
                        <input id="enddate" name="enddate" value="" placeholder="*结束日期：YYYY-MM-DD"
                               class="login" minlength="10" maxlength="10" required/>
                    </div> <!-- /field -->
                    <div class="field">
                        <label for="starttime">Start Time:</label>
                        <input id="starttime" name="starttime" value="" placeholder="*开始时间：HH:MM:SS"
                               class="login" minlength="8" maxlength="8" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="endtime">End Time:</label>
                        <input id="endtime" name="endtime" value="" placeholder="*结束时间： HH:MM:SS"
                               class="login" minlength="8" maxlength="8" required/>
                    </div> <!-- /field -->

                    <div class="field">
                        <label for="capa">Capacity:</label>
                        <input id="capa" name="capa" value="" placeholder="*课程容量" class="login" required/>
                    </div> <!-- /field -->
                    <div class="field">
                        <div class="group-form-input">
                            <label for="searchFValue">分配教师:</label>
                            <input id="searchFValue" placeholder="*分配教师" required>
                            <ul class="w_selected1">
                            </ul>
                        </div>
                    </div>
                    <div class="field">
                        <div class="group-form-input">
                            <label for="searchCValue">分配预选课程：</label>
                            <input id="searchCValue" placeholder="分配预选课程(非必填)">
                            <ul class="w_selected2">
                            </ul>
                        </div>

                    </div> <!-- /field -->
                </div> <!-- /login-fields -->
                <div class='w_day'>
                    <h4 class="status" style="margin-top: 0px;">*选择上课的时间: </h4>
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
                </div>
                <hr style="margin-top: 5px"/>
                <div class="login-actions" style="margin-top: -15px;">
                    <textarea id="comment" style="margin:5px 0px 0px 0px; width: 300px; height: 100px; resize: none;" placeholder="请输入备注信息"></textarea>
                    <span class="login-checkbox">
                        <input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
                        <label class="choice" for="Field">确认上述信息正确无误</label>
                    </span>
                    <button class="button btn btn-primary btn-large" id="registerBtn">创建</button>

                </div> <!-- .actions -->
            </form>
        </div> <!-- /content -->

    </div> <!-- /account-container -->
    <div id="course-div" style="float: right;position:absolute;width: 1563px;height: 700px; display: block"
         class="account-container register">
        <div class="content clearfix">
            <h1>课程列表</h1>
            <div class='course-table'>
                <table width="100%" style="font-size: 14px;" id="courseTable" class="display dataTable">
                </table>
            </div>
        </div> <!-- /content -->
    </div> <!-- /account-container -->
</div>

<div id="course" class="w_wrapper">
    <div class="w_wrap">
        <div class="w_head">
            <span>课程管理台</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body">
            <ul class="w_tab clearfix">
                <li class="active base-info">基本信息</li>
                <li class='account'> 课程设置</li>
                <li class="override">添加学生</li>
            </ul>

            <div class="w_tabC w_pop">
                <div class="w_content">
                    <div class="w_basicInfo account-container register" style="margin-left: 0px;width: 430px;">
                        <form id="editCourseForm" method="post" onsubmit="return false">
                            <table style="margin: 15px 27px;">
                                <tr>
                                    <td><p style="float: left">课程编号：</td>
                                    <td><input id="crn2" name="crn" value="" class="login" disabled/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">年-学期:</td>
                                    <td><input id="year-semester2" name="year-semester2" value="" class="login" disabled/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">课程名:</p></td>
                                    <td><input id="name2" name="name2" value=""class="login" minlength="1" maxlength="20" required/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">学分:</p></td>
                                    <td><input id="credits2" name="credits2" value="" class="login"
                                               minlength="1" maxlength="1" required/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">课程等级:</p></td>
                                    <td><input id="coulev2" name="coulev2" value="" class="login" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">班级:</p></td>
                                    <td> <input id="cousec2" name="cousec2" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">教室:</p></td>
                                    <td> <input id="classroom2" name="classroom2" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">开始日期:</p></td>
                                    <td><input id="startdate2" name="startdate2" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">结束日期:</p></td>
                                    <td><input id="enddate2" name="enddate2" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">开始时间:</p></td>
                                    <td><input id="starttime2" name="starttime2" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">结束时间:</p></td>
                                    <td><input id="endtime2" name="endtime2" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">容量:</p></td>
                                    <td><input id="capa2" name="capa2" value="" class="login"/></td>
                                </tr>
                                <tr>
                                    <td><p style="float: left">备注信息:</p></td>
                                    <td><textarea id="comment2" style="margin: 5px 0px 0px 0px; width: 250px; height: 100px; resize: none;"></textarea></td>
                                </tr>
                            </table>
                        </form>
                    </div> <!-- /account-container -->
                </div>
                <div class="w_buttons_w system-control-btn">
                    <button id="confirm" class="w_button">修改</button>
                    <button id="cancel" class="w_button">退出</button>
                </div>
            </div>
            <div class="w_tabD w_pop">
                <div class='w_manage'>
                    <h4 class="status" style="color:red; font-weight: 300; margin-top: 0px;">课程状态(无法修改): </h4>
                    <h4 class="type">授课老师: </h4>
                    <button class="w_button" style="width: 190px;height: 35px; vertical-align: middle;" id="assignF">修改授课老师</button>
                    <div id="assignFDiv" class="group-form-input">
                        <input id="searchFValue2" placeholder="修改授课老师" style="margin-right: 20px; height: 30px;float: left; width: 155px;" required>
                        <span class="w_button w_add" id="addf-button2" style="width: 145px; text-align: center;">修改授课老师</span>
                        <span class="w_button w_add" id="cancelF" style="width: 100px; text-align: center; background-color: #3d8ca7;">取消操作</span>
                        <ul class="w_selected3">
                        </ul>
                    </div>
                    <h4 class="gender">分配的预选课程：无</h4>
                    <button class="w_button" style="width: 190px;height: 35px; vertical-align: middle;" id="assignC">分配新的预选课程</button>
                    <div id="assignCDiv" class="group-form-input">
                        <input id="searchCValue2" placeholder="分配新的预选课程" style="margin-right: 20px; height: 30px;float: left; width: 155px;">
                        <span class="w_button w_add" id="addc-button2" style="width: 145px; text-align: center;">分配此预选课程</span>
                        <span class="w_button w_add" id="cancelC" style="width: 100px; text-align: center; background-color: #3d8ca7;">取消操作</span>
                        <ul class="w_selected4">
                        </ul>
                    </div>
                    <h4 class="status" style="margin-top: 0px;">当前课程的上课时间: </h4>
                    <input class='m' type="checkbox" name="newDay" value="m"
                           style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>星期一
                    <input class='t' type="checkbox" name="newDay" value="t"
                           style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>星期二
                    <input class='w' type="checkbox" name="newDay" value="w"
                           style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>星期三
                    <input class='tr' type="checkbox" name="newDay" value="tr"
                           style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>星期四
                    <input class='f' type="checkbox" name="newDay" value="f"
                           style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>星期五
                    <input class='sa' type="checkbox" name="newDay" value="sa"
                           style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>星期六
                    <input class='s' type="checkbox" name="newDay" value="s"
                           style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>星期日
                    <span class="w_button w_add" id="change-day" style="width: 145px; text-align: center;">修改时间</span>
                    <span class="w_button w_add" id="cancelD"    style="width: 145px; text-align: center;">取消操作</span>
                </div>
                <div class='w_manage_btn system-control-btn'>
                    <button id="cancel2" class="w_button">退出</button>
                </div>
            </div>
            <div class="w_tabE w_pop">
                <h4 class="type"></h4>
                <button class="w_button" style="width: 190px; vertical-align: middle;" id="assignS">添加学生</button>
                <div id="assignSDiv" class="group-form-input">
                    <h4 class="status" style="margin-top: 0px;">添加选项: </h4>
                    <table style="font-size: 11px">
                        <tr>
                            <td style="color: red;font-weight: 900;margin-bottom: -5px"><input type="checkbox" name="pre" style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>
                                添加此学生，即使未完成预选课程
                            </td>
                        </tr>
                        <tr>
                            <td style="color: red;font-weight: 900;"><input type="checkbox" name="time" style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>
                                添加此学生，即使有时间冲突
                            </td>
                        </tr>
                        <tr>
                            <td style="color: red;font-weight: 900;"><input type="checkbox" name="capa" style="height: 14px;margin: 10px 4px 10px 0; width: 12px;"/>
                                添加此学生，即使已经达到课程人数上限
                            </td>
                        </tr>
                    </table>
                    <hr style="margin-top: 1px;"/>
                    <h4 class="status" style="margin-top: -10px; margin-bottom: 5px">系统中所有学生: </h4>
                    <div class="content clearfix" style="padding: 0px">
                        <div class='course-table'>
                                <table width="100%" style="font-size: 14px;" id="studentTable" class="display dataTable">
                            </table>
                        </div>
                    </div> <!-- /content -->
                    <span class="w_button w_add" id="cancelS" style="width: 100px; text-align: center; background-color: #3d8ca7;">取消</span>
                </div>
                <div class='w_manage_btn system-control-btn'>
                    <button id="cancel3" class="w_button" style="margin: 20px 0 0 122px;width: 190px">退出</button>
                </div>
            </div>

        </div>
    </div>
</div>

<div id="student" class="w_wrapper">
    <div class="w_wrap">
        <div class="w_head">
            <span>课程控制台</span>
            <span class="w_close">×</span>
        </div>
        <div class="w_body" style="margin-top: 21px;">
            <div class="content clearfix" style="padding: 0px">
                <h4 class="type">该课程学生列表: </h4>
                <hr style="margin: 7px 0 7px 0;"/>
                <table width="100%" style="font-size: 14px;" id="studentList" class="display dataTable">
                </table>
            </div> <!-- /content -->
        </div>
        <div class='w_manage_btn system-control-btn'>
            <button id="cancel4" class="w_button" style="margin: -14px 0 25px 167px;width: 123px;">退出</button>
        </div>
    </div>
</div>

</body>
<script src="${basePath}/static/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/static/js/adminJs/manageCourse.js"></script>
</html>

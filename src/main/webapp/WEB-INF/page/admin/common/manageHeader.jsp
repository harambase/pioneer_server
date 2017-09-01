<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar" style="top: 0px;">
    <h1><i class="fa fa-bars push"></i>Administrator <span class="color">Manage</span></h1>
    <ul>
        <li><a href="${basePath}/welcomeAdmin"><i class="fa fa-dashboard push"></i>主页<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="${basePath}/manageCourse"><i class="fa fa-book push"></i>课程<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="${basePath}/manageUser"><i class="fa fa-user push"></i>用户<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="${basePath}/manageAdvising"><i class="fa fa-users push"></i>辅导关系<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="${basePath}/manageTranscript"><i class="fa fa-sticky-note push"></i>成绩单<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a id="logout"><i class="fa fa-plane push"></i>登出<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
    </ul>
</div>
<script src="${basePath}/static/js/otherJs/index.js"></script>
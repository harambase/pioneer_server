<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="${basePath}/static/css/header.css" type="text/css" rel="stylesheet"/>
<link href="${basePath}/static/plugins/font/font.css" type='text/css' rel="stylesheet">
<div class="sidebar">
    <h1><i class="fa fa-bars push"></i>Administrator <span class="color">Menu</span></h1>
    <ul>
        <li><a href="${basePath}/welcomeAdmin"><i class="fa fa-dashboard push"></i>主页<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="#"><i class="fa fa-user push"></i>管理<i class="fa fa-angle-right"></i></a><span class="hover"></span>
            <ul class="sub-menu">
                <li><a href="${basePath}/manageCourse">课程管理<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
                <li><a href="${basePath}/manageUser">用户管理<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
                <li><a href="${basePath}/manageAdvising">辅导管理<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
                <li><a href="${basePath}/manageTranscript">成绩单管理<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
            </ul>
        </li>
        <li><a href="${basePath}/adminProfile"><i class="fa fa-id-card push"></i>个人信息<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="${basePath}/welcome"><i class="fa fa-cog push"></i>选择接入系统<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a href="#" id="logout"><i class="fa fa-plane push"></i>登出<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
    </ul>
</div>

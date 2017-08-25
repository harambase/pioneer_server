<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonJs.jsp" %>
<%@include file="../common/commonCSS.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard | Dashboard</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="${basePath}/static/plugins/bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${basePath}/static/plugins/admin/main.css">
</head>
<body>
<div>
    <div id="wrapper">
        <!--BEGIN SIDEBAR MENU-->
        <%@include file="common/welcomeHeader.jsp" %>
        <!--END SIDEBAR MENU-->
        <!--BEGIN PAGE WRAPPER-->
        <div id="page-wrapper">
            <!--BEGIN TITLE & BREADCRUMB PAGE-->
            <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
                <div class="site-branding-text">
                    <div class="page-title" style="clear: none;font-size: 24px;font-weight: 800;line-height: 1.25;letter-spacing: 0.08em;margin: 0;padding: 0;text-transform: uppercase;color: #03ced0;">
                        Administrator Manage Site
                    </div>
                    <p class="site-description">Dashboard</p>
                </div>
                <div class="clearfix">
                </div>
            </div>
            <!--END TITLE & BREADCRUMB PAGE-->
            <!--BEGIN CONTENT-->
            <div class="page-content">
                <div id="tab-general">
                    <div id="sum_box" class="row mbl">
                        <div class="col-sm-6 col-md-3">
                            <div class="panel profit db mbm">
                                <div class="panel-body">
                                    <p class="icon">
                                        <i class="icon fa fa-shopping-cart"></i>
                                    </p>

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-3">
                            <div class="panel income db mbm">
                                <div class="panel-body">
                                    <p class="icon">
                                        <i class="icon fa fa-money"></i>
                                    </p>

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-3">
                            <div class="panel task db mbm">
                                <div class="panel-body">
                                    <p class="icon">
                                        <i class="icon fa fa-signal"></i>
                                    </p>

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-3">
                            <div class="panel visit db mbm">
                                <div class="panel-body">
                                    <p class="icon">
                                        <i class="icon fa fa-group"></i>
                                    </p>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mbl">
                        <div class="col-lg-8">
                            <div class="panel">
                                <div class="row text-center divider" style="margin: 5px 5px 0px 5px;">
                                    <div class="col-xs-12 col-sm-4 emphasis">
                                        <button  id="user" class="btn btn-yellow btn-block">
                                            <span class="fa fa-user"></span>User Chart
                                        </button>
                                    </div>
                                    <div class="col-xs-12 col-sm-4 emphasis">
                                        <button id="connection" class="btn btn-blue btn-block">
                                            <span class="fa fa-plane"></span>Relational Chart
                                        </button>
                                    </div>
                                </div>
                                <hr style="margin-bottom: 0px"/>
                                <div id="chart">
                                    <div class="panel-body" style="width: 100%; height: 400px;">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <h4 class="mbs">
                                                    Pioneer System User Formation Chart
                                                </h4>
                                                <p class="help-block">
                                                    By Type, By Gender
                                                </p>
                                                <div id="area-chart-spline" style="width: 800px; height: 300px">
                                                    <div id="exampleChart" style="width: 800px; height: 300px"></div>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <h4 class="mbm">Top Courses</h4>
                                                <span class="task-item">CPU Usage (25 - 32 cpus)
                                                    <small class="pull-right text-muted">40%</small>
                                                    <div class="progress progress-sm">
                                                        <div role="progressbar" aria-valuenow="40" aria-valuemin="0"
                                                             aria-valuemax="100"
                                                             style="width: 40%;" class="progress-bar progress-bar-orange">
                                                            <span class="sr-only">40% Complete (success)</span>
                                                        </div>
                                                </div>
                                                </span>
                                                <span>Memory Usage (2.5GB)<small class="pull-right text-muted">60%</small>
                                                    <div class="progress progress-sm">
                                                        <div role="progressbar" aria-valuenow="60" aria-valuemin="0"
                                                             aria-valuemax="100"
                                                             style="width: 60%;" class="progress-bar progress-bar-blue">
                                                            <span class="sr-only">60% Complete (success)</span>
                                                        </div>
                                                    </div>
                                                </span>
                                                <span>Disk Usage (C:\ 120GB , D:\ 430GB)
                                                    <small class="pull-right text-muted">55%</small>
                                                    <div class="progress progress-sm">
                                                        <div role="progressbar" aria-valuenow="55" aria-valuemin="0"
                                                             aria-valuemax="100"
                                                             style="width: 55%;" class="progress-bar progress-bar-green">
                                                            <span class="sr-only">55% Complete (success)</span>
                                                        </div>
                                                    </div>
                                                </span>
                                                <span>Domain (2/5)<small class="pull-right text-muted">66%</small>
                                                    <div class="progress progress-sm">
                                                        <div role="progressbar" aria-valuenow="66" aria-valuemin="0"
                                                             aria-valuemax="100"
                                                             style="width: 66%;" class="progress-bar progress-bar-yellow">
                                                            <span class="sr-only">66% Complete (success)</span>
                                                        </div>
                                                    </div>
                                                </span>
                                                <span>Database (90/100)<small class="pull-right text-muted">90%</small>
                                                    <div class="progress progress-sm">
                                                        <div role="progressbar" aria-valuenow="90" aria-valuemin="0"
                                                             aria-valuemax="100"
                                                             style="width: 90%;" class="progress-bar progress-bar-pink">
                                                            <span class="sr-only">90% Complete (success)</span></div>
                                                    </div>
                                                </span>
                                                <span>Email Account (25/50)<small class="pull-right text-muted">50%</small>
                                                    <div class="progress progress-sm">
                                                        <div role="progressbar" aria-valuenow="50" aria-valuemin="0"
                                                             aria-valuemax="100"
                                                             style="width: 50%;" class="progress-bar progress-bar-violet">
                                                            <span class="sr-only">50% Complete (success)</span></div>
                                                    </div>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="relation">
                                    <div class="panel-body" style="width: 100%; height: 900px;">
                                        <div class="row">
                                            <h4 class="mbs" style="margin-left: 600px;font-weight: 500;color: #03ced0;">
                                                Pioneer SFC Relational Chart
                                            </h4>
                                            <div class="col-md-8" style="margin-left: 50px;">
                                                <p class="help-block">
                                                    SFC: Student-Faculty-Course Combining View
                                                </p>
                                                <div class="exampleChart" >
                                                    <div id="relationChart"style="width: 800px;height: 800px;"></div>
                                                </div>
                                            </div>
                                            <div class="col-md-4" style="margin-left: -200px;">
                                                <p class="help-block">
                                                    SFC: Student-Faculty-Course Separating View
                                                </p>
                                                <div class="exampleChart" >
                                                    <div id="relationChart2"style="width: 800px;height: 800px;"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--<div class="row mbl">--%>
                        <%--<div class="col-lg-8">--%>
                            <%--<div class="portlet box">--%>
                                <%--<div class="portlet-header">--%>
                                    <%--<div class="caption">--%>
                                        <%--Todo List--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div style="overflow: hidden;" class="portlet-body">--%>
                                    <%--<ul class="todo-list sortable">--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Sed ut perspiciatis unde omnis iste--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--At vero eos et accusamus et iusto odio dignissimos ducimus qui--%>
                                                <%--blanditiis praesentium--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil--%>
                                                <%--impedit quo--%>
                                                <%--minus id--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Et harum quidem rerum facilis est--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Excepteur sint occaecat cupidatat non proident--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row mbl">--%>
                        <%--<div class="col-lg-4">--%>
                            <%--<div class="panel">--%>
                                <%--<div class="panel-body">--%>
                                    <%--<div class="profile">--%>
                                        <%--<div style="margin-bottom: 15px" class="row">--%>
                                            <%--<div class="col-xs-12 col-sm-8">--%>
                                                <%--<h2>John Doe</h2>--%>
                                                <%--<p>--%>
                                                    <%--<strong>About:</strong> Web Designer / UI.</p>--%>
                                                <%--<p>--%>
                                                    <%--<strong>Hobbies:</strong> Read, out with friends, listen to music,--%>
                                                    <%--draw and learn--%>
                                                    <%--new things.</p>--%>
                                                <%--<p>--%>
                                                    <%--<strong class="mrs">Skills:</strong><span--%>
                                                        <%--class="label label-green mrs">html5</span><span--%>
                                                        <%--class="label label-green mrs">css3</span><span--%>
                                                        <%--class="label label-green mrs">jquery</span></p>--%>
                                            <%--</div>--%>
                                            <%--<div class="col-xs-12 col-sm-4 text-center">--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="row text-center divider">--%>
                                            <%--<div class="col-xs-12 col-sm-4 emphasis">--%>
                                                <%--<h2>--%>
                                                    <%--<strong>20,7K</strong></h2>--%>
                                                <%--<p>--%>
                                                    <%--<small>Followers</small>--%>
                                                <%--</p>--%>
                                                <%--<button class="btn btn-yellow btn-block">--%>
                                                    <%--<span class="fa fa-plus-circle"></span>&nbsp; Follow--%>
                                                <%--</button>--%>
                                            <%--</div>--%>
                                            <%--<div class="col-xs-12 col-sm-4 emphasis">--%>
                                                <%--<h2>--%>
                                                    <%--<strong>245</strong></h2>--%>
                                                <%--<p>--%>
                                                    <%--<small>Following</small>--%>
                                                <%--</p>--%>
                                                <%--<button class="btn btn-blue btn-block">--%>
                                                    <%--<span class="fa fa-user"></span>&nbsp; Profile--%>
                                                <%--</button>--%>
                                            <%--</div>--%>
                                            <%--<div class="col-xs-12 col-sm-4 emphasis">--%>
                                                <%--<h2>--%>
                                                    <%--<strong>43</strong></h2>--%>
                                                <%--<p>--%>
                                                    <%--<small>Snippets</small>--%>
                                                <%--</p>--%>
                                                <%--<div class="btn-group dropup">--%>
                                                    <%--<button type="button" data-toggle="dropdown"--%>
                                                            <%--class="btn btn-orange dropdown-toggle">--%>
                                                        <%--<span class="fa fa-gear"></span>&nbsp; Options--%>
                                                    <%--</button>--%>
                                                    <%--<ul role="menu" class="dropdown-menu pull-right text-left">--%>
                                                        <%--<li><a href="#"><span class="fa fa-envelope"></span>&nbsp; Send--%>
                                                            <%--an email</a></li>--%>
                                                        <%--<li><a href="#"><span class="fa fa-list"></span>&nbsp; Add or--%>
                                                            <%--remove from a list</a></li>--%>
                                                        <%--<li class="divider"></li>--%>
                                                        <%--<li><a href="#"><span class="fa fa-warning"></span>&nbsp; Report--%>
                                                            <%--this user for spam</a></li>--%>
                                                        <%--<li class="divider"></li>--%>
                                                        <%--<li><a href="#" role="button" class="btn disabled">Unfollow</a>--%>
                                                        <%--</li>--%>
                                                    <%--</ul>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-8">--%>
                            <%--<div class="portlet box">--%>
                                <%--<div class="portlet-header">--%>
                                    <%--<div class="caption">--%>
                                        <%--Todo List--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div style="overflow: hidden;" class="portlet-body">--%>
                                    <%--<ul class="todo-list sortable">--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Sed ut perspiciatis unde omnis iste--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--At vero eos et accusamus et iusto odio dignissimos ducimus qui--%>
                                                <%--blanditiis praesentium--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil--%>
                                                <%--impedit quo--%>
                                                <%--minus id--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Et harum quidem rerum facilis est--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="clearfix"><span class="drag-drop"><i></i></span>--%>
                                            <%--<div class="todo-check pull-left">--%>
                                                <%--<input type="checkbox" value=""/></div>--%>
                                            <%--<div class="todo-title">--%>
                                                <%--Excepteur sint occaecat cupidatat non proident--%>
                                            <%--</div>--%>
                                            <%--<div class="todo-actions pull-right clearfix">--%>
                                                <%--<a href="#" class="todo-complete"><i class="fa fa-check"></i></a><a--%>
                                                    <%--href="#" class="todo-edit">--%>
                                                <%--<i class="fa fa-edit"></i></a><a href="#" class="todo-remove"><i--%>
                                                    <%--class="fa fa-trash-o">--%>
                                            <%--</i></a>--%>
                                            <%--</div>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>

            <!--END CONTENT-->
            <!--BEGIN FOOTER-->
            <%--<div id="footer">--%>
                <%--<div class="copyright">--%>
                    <%--<b>--%>
                        <%--<%@include file="../common/commonFooter.jsp" %>--%>
                    <%--</b>--%>
                <%--</div>--%>
                <%--<!--END FOOTER-->--%>
            <%--</div>--%>
            <!--END PAGE WRAPPER-->
        </div>
    </div>
</div>
<script src="${basePath}/static/plugins/echarts/echarts.min.js"></script>
<script src="${basePath}/static/js/otherJs/chart.js"></script>
<script src="${basePath}/static/js/otherJs/dataTool.js"></script>
<script src="${basePath}/static/js/adminJs/welcomeAdmin.js"></script>

</body>
</html>

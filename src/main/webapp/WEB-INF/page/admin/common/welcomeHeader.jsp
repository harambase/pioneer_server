<link rel="stylesheet" href="${basePath}/static/css/header.css" media="screen" type="text/css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<div class="sidebar">
    <h1><i class="fa fa-bars push"></i>Administrator <span class="color">Menu</span></h1>
    <ul>
        <li>
            <a href="${basePath}/welcomeAdmin">
                <i class="fa fa-dashboard push"></i>Dashboard<i class="fa fa-angle-right"></i>
            </a>
            <span class="hover"></span>
        </li>
        <li><a href="#"><i class="fa fa-user push"></i>Manage<i class="fa fa-angle-right"></i></a>
            <span class="hover"></span>
            <ul class="sub-menu">
                <li><a href="${basePath}/manageCourse">Course Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
                <li><a href="${basePath}/manageUser">User Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
                <li><a href="${basePath}/manageAdvising">Advising Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
                <li><a href="${basePath}/manageTranscript">Transcript Manage<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
            </ul>
        </li>
        <li><a href="#"><i class="fa fa-file push"></i>Information<i class="fa fa-angle-right"></i></a><span class="hover"></span>
            <ul class="sub-menu">
                <li><a href="#">Latest Messages<i class="fa fa-angle-right"></i></a><span class="hover"></span>
                </li>
                <li><a href="#">View Advisor Profile<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
            </ul>
        </li>
        <li>
            <a href="${basePath}/adminProfile">
            <i class="fa fa-cog push"></i>Profile<i class="fa fa-angle-right"></i>
            </a>
            <span class="hover"></span>
        </li>
        <li><a href="#"><i class="fa fa-plane push"></i>Contact<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
        <li><a id="logout"><i class="fa fa-plane push"></i>Logout<i class="fa fa-angle-right"></i></a><span class="hover"></span></li>
    </ul>
</div>
<script src="${basePath}/static/js/otherJs/index.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Update Status</title>
</head>
<body>
<div id="header">
	<div class="logo">
		<img src="../Images/HaramBaseICON.png">
	</div>
</div>
<ul>
	<li class="active">
	<a href="${basePath}/WelcomeMember.do">Welcome</a>
	<c:if test="${member.isbuyer == 1}">

		<li class="dropdown">
		<a href="javascript:void(0)" class="dropbtn">Buying Management Tools</a>
		<div class="dropdown-content">
			<a href="${basePath}/ListOfBidOn.do">List Of Bids</a>
			<a href="${basePath}/Search.do">Search</a>
			<a href="${basePath}/ListOfBought.do">List of Bought</a>
		</div>
	</c:if>

	<c:if test="${member.iseller == 1}">
		<li class="dropdown">
		<a href="javascript:void(0)" class="dropbtn">Selling management Tools</a>
		<div class="dropdown-content">
			<a href="${basePath}/AddItem.do">Add an Item</a>
			<a href="${basePath}/ListItem.do">List of Selling</a>
			<a href="${basePath}/ViewMyFeedback.do">View my Rating</a>
		</div>
	</c:if>
	<li class="dropdown">
	<a href="javascript:void(0)" class="dropbtn">Member Management Tool</a>
	<div class="dropdown-content">
	<a href="${basePath}/Edit.do">Update Profiles</a>
	</div>
	</li>
	<li class="dropdown", style="float:bottom">
	<form action="${basePath}/Logout_action.do" method="post" >
	<input class = "btnlogout" name="Submit" value="Logout" type="submit">
	</form>
	</li>
	</ul>

<c:if test="${succ != 1}">
	<h1>Update Failed</h1>
	<c:if test="${succ == -2}">
		<p> Error! Invalid password!</p>
	</c:if>
	<c:if test="${succ == -1}">
		<p> Error!! New Password does not match</p>
	</c:if>
	<c:if test="${succ == 0}">
		<p> Error!! SQL not executed</p>
	</c:if>
</c:if>
<c:if test="${succ == 1}">
	<h1>Update Successful</h1>
</c:if>

<button style="margin-left: 15%;" onclick="window.history.go(-1);">
	Back to Edit
</button>
</body>
</html>
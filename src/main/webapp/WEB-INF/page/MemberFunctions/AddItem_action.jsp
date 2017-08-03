<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Add Item Status</title>
</head>
<body>
<div id="header">
	<div class="logo">
		<img src="../Images/HaramBaseICON.png">
	</div>
</div>
<ul>
	<li class="status">
	<a href="WelcomeMember.do">Welcome</a>
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

	<c:if test="${succ[0] == 0 && succ[1] == 0}">
		<h1>Success!</h1>
		<form method="post" action="${bathPath}/ListItem.do">
			<input class = "btn" name="Submit" value="List of Items" type="submit">
		</form>
	</c:if>
	<p><b>
	<c:if test="${succ[0] == -2 || succ[1] == -1}">
		<h1>Add Failed</h1>
		<h2>Item Information Error! Possible Cause:</h2>
	<c:if test="${succ[0] == -2}">

			<br>  Start Price is less or equal to zero.
			<br>  Some Required field is empty
	</c:if>
	<c:if test="${succ[1] == -1}">
		    <br>  Start Time must be in the future compare to today.
		    <br>  End Time must be in the future compare to start time.

	</c:if>
	</b></p>
		<button style="margin-left: 15%;" onclick="window.history.go(-1);">
		Return
	</button>
	</c:if>
</body>
</html>
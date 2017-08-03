<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Bid Status</title>
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

	<h1>Bid Status</h1>
	<c:if test="${succ == 0}">
		<h2> SUCCESS! </h2>
		<form method="post" action="${bathPath}/ListOfBidOn.do">
			<input class = "btn" name="Submit" style="margin-left: 10%" value="List of Bid Items" type="submit">
		</form>
	</c:if>
	<c:if test="${succ != 0}">
		<c:if test="${succ == 3}">
			<h2> Error!! The bidding price you submitted is lower than the minimum bidding price!</h2>
		</c:if><c:if test="${succ == 5}">
			<h2> Error!! You currently are the Winner!</h2>
		</c:if><c:if test="${succ == 4}">
			<h2> Error!! You are the Seller!</h2>
		</c:if><c:if test="${succ == 0}">
			<h2> Error!! SQL not executed</h2>
		</c:if>
		<button style="margin-left: 15%;" onclick="window.history.go(-1);">
			Back to re-bid
		</button>
	</c:if>

	</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Bid</title>
</head>
<body>
<div id="header">
	<div class="logo">
		<img src="Images/HaramBaseICON.png">
	</div>
</div>
<ul>
	<li class="active">
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
<h1>Bid this Item</h1>
<form method="post" action="${basePath}/Bid_action.do" name="EditForm">
<div id="table">
<table style="text-align: center; width: 35%; margin-left: 17%; margin-right: auto; border: 1px solid;">

	<tr>
    	<td>Item ID</td>
    	<td>${itemid}</td>
		<input name="itemid" class="btn" type="hidden" value="${itemid}">
		<input name="sellerid" class="btn" type="hidden" value="${itemInfo.sellerid}">
		<input name="winner" class="btn" type="hidden" value="${itemInfo.winner}">
    </tr>
	<tr>
   		<td>Item Name</td>
   		<td>${itemInfo.itemName}</td>
    </tr>
	<tr>
    	<td>Start Selling Price </td>
    	<td>$${itemInfo.startPrice}</td>
    </tr>
	<c:if test="${itemInfo.currentPrice != 0}">
    <tr>
    	<td>Current Max Bid </td>
    	<td>$${max}</td>
    </tr>
    <tr>	
    	<td>Min Bidding Price </td>
    	<td>$${max+1}</td>
		<input name="minBid" class="btn" type="hidden" value="${max+1}">
    </tr>
	</c:if>
	<c:if test="${itemInfo.currentPrice == 0}">
	<tr>
		<td>Current Max Bid </td>
		<td>$0.00</td>
	</tr>
	<tr>
		<td>Min Bidding Price </td>
		<td>$${itemInfo.startPrice+1}</td>
		<input name="minBid" class="btn" type="hidden" value="${itemInfo.startPrice+1}">

	</tr>
	</c:if>
	<tr>
   		<td>Type You Bid</td>
   		<td>$<input name="maxBidLimit" value=""></td>
    </tr>

</tbody>
</table>
</div>
	<input name="Submit" class="btn" style="margin-right: 33%" value="Bid On This Item" type="submit"><br>
	<input name="userID" class="btn" type="hidden" value="${member.userid}">
</form>
</body>
</html>
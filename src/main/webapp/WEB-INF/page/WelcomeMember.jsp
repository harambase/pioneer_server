<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en"><head>

		<link rel="stylesheet" type="text/css" href="../../DropdownMenu.css">
		<link rel="stylesheet" type="text/css" href="../../Harambase.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Harambase</title>

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
	   <div id="title">
		   <header class="mega-header">
			 	<h2>Welcome Back ${member.fname}!</h2>
	 			<p> UserID: ${member.userid}</p>
			</header>
		</div>

		<p style="margin-right:20%">GABeS is an online information system that operates on top of a database backend in order to manage users and auctions.
			There are two distinct types of users: Administrators and Customers; customers also come in two flavors: Sellers and
			Bidders. Unique customer IDs are assigned to customers to keep track of them. A customer may update their own user
			profile; each customer has an email address and a unique username in addition to other pertinent information as
			described later on in this document. Sellers post item(s) they want to auction/sell and bidders bid on any item of interest
			as long as the item is on auction. Auction winners pay the current (winning) bid for items won (for now, payments occur
			outside GABeS). When posting new items for auction/sale, a seller must specify all item information (item name, item
			description, etc.), starting bid, and the auction end date. The system generates unique item IDs to keep track of auction
			items.
		</p>
		<div id="footer">
		<div id="copyright">
		<p><a>Copyright &copy; 2016</a> - All Rights Reserved<br>
		<a>Harambase Development Team</a></p>
		</div>
	</div>
	</body>
</html>
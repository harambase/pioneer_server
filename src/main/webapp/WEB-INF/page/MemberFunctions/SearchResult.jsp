<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Search Result</title>
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
<h1>Search Result</h1>
		
<div id="table">
<table style="text-align: center; margin-left: 17%; margin-right: auto; border: 1px solid;">
  <tbody>
  <tr>
      	<td style="vertical-align: middle;"><b>itemID</b><br> 
      	</td>
      	<td style="vertical-align: middle;"><b>Item Name</b><br> 
      	</td>
      	<td style="vertical-align: middle;"><b>Category</b><br>
      	</td>
     	 <td style="vertical-align: middle;"><b>Auction Start Time</b><br>
      	</td>
      	<td style="vertical-align: middle;"><b>Auction End Time</b><br>
      	</td>
      	<td style="vertical-align: middle;"><b>Current Price</b><br>
      	</td>
      	<td style="vertical-align: middle;"><b>View Item Info</b><br>
      	</td>
      	<td style="vertical-align: middle;"><b>Bid On this Item</b><br>
      	</td>
   </tr>

  	<c:forEach var="item" items="${searchResult}">
		<c:if test="${item.status != -1}">
		<tr>
			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				<c:out value="${item.itemid}"/>
			</td>

			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				<c:out value="${item.itemname}"/>
			</td>

			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				<c:out value="${item.itemCategory}"/>
			</td>

			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				<c:out value="${item.auctionstarttime}"/>
			</td>

			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				<c:out value="${item.auctionendtime}"/>
			</td>

			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				<c:out value="$${item.currentbid}"/>
			</td>

			<td style="vertical-align: middle">
				<form method="post" action="${basePath}/ItemInfo.do">
					<input class = "btn" name="Submit" value="item-info" type="submit">
					<input name="ITEMID" type="hidden" value ="${item.itemid}">
				</form>
			</td>
			<c:if test="${item.status == 0}">
			<td>
				<form method="post" action="${basePath}/Bid.do">
					<input name="submit" id="btn" type="submit" class="btn" value="Bid">
					<input name="ITEMID" type="hidden" value ="${item.itemid}">
				</form>
			</td>
			</c:if>
			<c:if test="${item.status == 1}">
				<td>
					ITEM SOLD
				</td>
			</c:if>
		</tr>
		</c:if>
	</c:forEach>
  </tbody>
</table>
</div>
<button style="margin-left: 15%;" onclick="window.history.go(-1);">
	Return
</button>
</body>
</html>
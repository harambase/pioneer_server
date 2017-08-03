<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Item List</title>
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

<h1>List of Your Items</h1>

<div id="table">
<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
  <tbody>
    <tr>
      	<td style="vertical-align: top;"><b>itemID</b>
      	</td>
      	<td style="vertical-align: top;"><b>Item Name</b> 
      	</td>
      	<td style="vertical-align: top;"><b>Category</b>
      	</td>
     	 <td style="vertical-align:top;"><b>Start Time</b>
      	</td>
      	<td style="vertical-align: top;"><b>End Time</b>
      	</td>
      	<td style="vertical-align: top;"><b>Start Price</b>
      	</td>
      	<td style="vertical-align: top;"><b>Current Bid</b>
      	</td>
      	<td style="vertical-align: top;"><b>Status</b>
      	</td>
      	<td style="vertical-align: top;"><b>Item Info</b>
      	</td>
      	<td style="vertical-align: top;"><b>Bidder List</b>
      	</td>
   </tr>
	<c:forEach items="${itemList}" var="item">

	<tr>
     	<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
			<c:out value="${item.itemid}"/>
     	</td>
     	
     	<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
			<c:out value="${item.itemName}"/>
     	</td>
     	
     	<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
			<c:out value="${item.itemCategory}"/>
     	</td>
     	
    	<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
			<c:out value="${item.auctionStartTime}"/>
    	</td>
    	
    	<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
			<c:out value="${item.auctionEndTime}"/>
    	</td>
    	
    	<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
			<c:out value="$${item.startPrice}"/>
    	</td>
    	<c:if test="${item.currentPrice != 0}">
			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				<c:out value="$${item.currentPrice}"/>
			</td>
		</c:if>

		<c:if test="${item.currentPrice == 0}">
			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				$0.00
			</td>
		</c:if>

		<c:if test="${item.status == 1}">
			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				SOLD
			</td>
		</c:if>
		<c:if test="${item.status == 0}">
			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				ON AUCTION
			</td>
		</c:if>
		<c:if test="${item.status == -1}">
			<td style="vertical-align: middle; text-align:center;" contenteditable='false'>
				NOT ON AUCTION
			</td>
		</c:if>


		<td style="vertical-align: middle">
			<form method="post" action="${bathPath}/ItemInfo.do">
				<input class = "btn" name="Submit" value="item-info" type="submit">
				<input class = "btn" name="ITEMID" type="hidden" value ="${item.itemid}">
				<input class = "btn" name="LISTITEM" type="hidden" value ="1">
			</form>
		</td>
		<c:if test="${item.status != -1}">
		<td  style="vertical-align: middle">
			<form method="post" action="${bathPath}/BidderList.do">
				<input class = "btn" name="Submit" value="bidder-List" type="submit">
				<input class = "btn" name="ITEMID" type="hidden" value ="${item.itemid}">
			</form>
		</td>
		</c:if>
		<c:if test="${item.status == -1}">
			<td  style="vertical-align: middle">
				NOT ON AUCTION
			</td>
		</c:if>
    </tr>
	</c:forEach>
   </tbody>
 </table>
 </div>
<form method="post" action="${bathPath}/AddItem.do" >
	<input class = "btn" name="Submit" style="margin-left: 27%" value="Add an Item" type="submit">
</form>
</body>
</html>
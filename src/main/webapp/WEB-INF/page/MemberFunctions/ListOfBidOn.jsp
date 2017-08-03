<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>List Of Items Bid On</title>
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
	<h1>List of Items Bid On</h1>

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
     	 <td style="vertical-align:top;"><b>Auction Start Time</b>
      	</td>
      	<td style="vertical-align: top;"><b>Auction End Time</b>
      	</td>
      	<td style="vertical-align: top;"><b>Current Price</b>
      	</td>
      	<td style="vertical-align: top;"><b>Winner</b>
      	</td>
      	<td style="vertical-align: top;"><b>Status</b>
		</td>
		<td style="vertical-align: top;"><b>Re-Bid</b>
		</td>
   </tr>
	<c:forEach items="${bidonList}" var="item">
	<tr>
     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.itemid}"/>
     	</td>
     	
     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.itemname}"/>
     	</td>
     	
     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.itemcategory}"/>
     	</td>
     	
    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.auctionstarttime}"/>
    	</td>
    	
    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.auctionendtime}"/>
    	</td>
    	
    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.currentPrice - 1}"/>
    	</td>

		<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.winner}"/>
		</td>
		<c:if test="${item.status == 0}">
			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<p>ON AUCTION</p>
			</td>

			<td>
				<form method="post" action="Bid.do">
					<input name="Submit" value="RE-bid" class="btn" type="submit"><br>
					<input name="ITEMID" type="hidden" value ="${item.itemid}">
				</form>
			</td>
		</c:if>
		<c:if test="${item.status == 1}">
			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<p>SOLD</p>
			</td>
			<td>
			</td>
		</c:if>
    </tr>
	</c:forEach>
   </tbody>
 </table>
</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>List of Bidders</title>
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
<h1>List of Bidders</h1>

<h2>Item ID: ${item.itemid}</h2>
<h2>(${item.auctionstarttime}  ---  ${item.auctionendtime})</h2>
<div id="table">
<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
  <tbody>
  	<tr>
  		<td style="vertical-align: top; text-align:left;"><b>Bidding Time</b><br> 
      	</td>
      	<td style="vertical-align: top; text-align:left;"><b>User Name</b><br> 
      	</td>
      	<td style="vertical-align: top; text-align:left;"><b>Bidding Price</b><br> 
      	</td>
  	</tr>
	<c:forEach items="${bidderList}" var="bid">
	<tr>
     	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			<c:out value="${bid.biddingtime}"/>
     	</td>

     	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			<c:out value="${bid.bidderuname}"/>
     	</td>	
     	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			<c:out value="${bid.maxbidlimit}"/>
     	</td>

    </tr>
	</c:forEach>
   	<c:if test="${bidderList[0].status == 1}">
		<tr>
			<td style="vertical-align: top; text-align:left;" contenteditable='false'><b>Winner</b></td>
			<td style="vertical-align: top; text-align:left;" contenteditable='false'><b>${bidderList[0].winner}</b></td>
			<td style="vertical-align: top; text-align:left;" contenteditable='false'><b>$${bidderList[0].soldprice}</b></td>
		</tr>
	</c:if>

   </tbody>
 </table>
</div>
<form method="post" style="margin-left: 59%" action="${bathPath}/ListItem.do">
	<input class = "btnreturn" name="Submit" value="RETURN" type="submit">
</form>
</body>
</html>
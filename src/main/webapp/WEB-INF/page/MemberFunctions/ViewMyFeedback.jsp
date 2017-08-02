<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>View my Feedback</title>
</head>
<body>
   	<div id="header">
			<div class="logo">
				<img src="../Images/HaramBaseICON.png">
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
	<h1>View my Feedback</h1>
	<c:if test="${numOfRec > 0}">
	<div id="table">
	<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
	  <tbody>
		<tr>
			<td style="vertical-align: top;"><b>IteamID</b>
			</td>
			<td style="vertical-align: top;"><b>Overall Rating</b>
			</td>
			 <td style="vertical-align:top;"><b>Item Quality</b>
			</td>
			<td style="vertical-align: top;"><b>Delivery</b>
			</td>
			<td style="vertical-align: top;"><b>Comments</b>
			</td>
			<td></td>
			<td></td>
	   </tr>
		<c:forEach var="item" items="${feedbackList}">
		<tr>
			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.itemid}"/>
			</td>

			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.overallrating}"/>
			</td>

			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.itemquality}"/>
			</td>

			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.delivery}"/>
			</td>

			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.comments}"/>
			</td>
		</tr>
		</c:forEach>
	   </tbody>
	 </table>
	 </div>
	 <h2>Summary:</h2>
	 <p>Average Rating:${avgRate}</p>
	 <p>Number of Records: ${numOfRec}</p>
	</c:if>
	<c:if test="${numOfRec == 0}">
			<h2> There is no records </h2>
	</c:if>
</body>
</html>

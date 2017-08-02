<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Update Profile</title>
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
<h1>Update Profile</h1>

<form method="post" action="${basePath}/Edit_action.do" name="Update">
<div id="table">
<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
<tbody>
		<tr>
			<td>Username</td>
			<td>${member.userid}</td>
		</tr>
	    <tr>
	    	<td>Username</td>
			<td> <input type="text"  name="uname" value="${member.uname}"></td>
	    </tr>
	    <tr>
	    	<td>First Name</td>  
	    	<td> <input type="text"  name="fname" value="${member.fname}"></td>
	    </tr>
	    
	    <tr>
	    	<td>Last Name</td>  
	    	<td> <input type="text"  name="lname" value="${member.lname}"></td>
	    </tr>
	    
	    <tr>
	    	<td>Email</td>  
	    	<td> <input type="text"  name="email" value="${member.email}"></td>
	    </tr>
	    <c:if test="${member.iseller == 1}">
	    <tr>
	    	<td>Seller Rating</td>  
	    	<td></td>
	    </tr>

	    <tr>
	    	<td>Number of Seller Ratings</td>  
	    	<td></td>
	    </tr>
		</c:if>
	    <tr>
	    	<td>Password</td>  
	    	<td> <input name="password" type = "password" value=""></td>
	    </tr>
	    
	    <tr>
	    	<td>New Password</td>  
	    	<td> <input name="NEWPASSWORD1" type = "password" value=""></td>
	    </tr>
	    
	    <tr>
	    	<td>Retype Password</td>  
	    	<td> <input name="NEWPASSWORD2" type = "password" value=""></td>
	    </tr>
	    
</tbody>
</table> 
</div>
	<input name="Submit" class = "btnlogout" style = "margin-right:-9%" value="Update" type="submit">
</form>
</body>
</html>

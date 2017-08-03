<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
		<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
		<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
		<link rel="stylesheet" type="text/css" href="Admin.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>View All Users</title>
</head>
	
<body>
	<div id="header">
		<div class="logo">
			<img src="../Images/HaramBaseICON.png">
		</div>
	</div>
	<ul>
		<li class="status">
		<a href="${basePath}/WelcomeAdmin.do">Welcome</a>
		</li>
		<li class="dropdown">
		<a href="${basePath}/OverallComm.do">View Overall Commission Report</a>
		</li>
		<li class="dropdown">
			<a href="${basePath}/SalesReport.do">View Sales Summary Report</a>
		</li>
		<li class="dropdown">
			<a href="${basePath}/ViewUsers.do" class="dropbtn">View Members and add Member</a>
		</li>

		<li class="dropdown", style="float:bottom">
			<form method="post" action="${basePath}/Logout_action.do">
			<input class = "btnlogout" name="Submit" value="Logout" type="submit"><br>
		</form>
		</li>
	</ul>
	<h1>View All Users</h1>
	<div class="table">
	<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
	  <tbody>
	    <tr>
	      	<td style="vertical-align: top;"><b>UserID</b><br> 
	      	</td>
	      	<td style="vertical-align: top;"><b>User Name</b><br> 
	      	</td>
	      	<td style="vertical-align: top;"><b>First Name</b><br>
	      	</td>
	     	 <td style="vertical-align:top;"><b>Last Name</b><br>
	      	</td>
	      	<td style="vertical-align: top;"><b>Email</b><br>
	      	</td>
	      	<td style="vertical-align: top;"><b>Password</b><br>
	   </tr>

		<c:forEach var="item" items="${memberMap}">
	    <tr>		
			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.userid}"/><br>
	     	</td>
	     	
	     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.uname}"/><br>
	     	</td>
	     	
	     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.fname}"/><br>
	     	</td>
	     	
	    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.lname}"/><br>
	    	</td>
	    	
	    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.email}"/><br>
	    	</td>
	    	
	    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<c:out value="${item.password}"/><br>
	   	</td>
	    	</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
	<h2>Add User</h2>


<form method="post" action="${bathPath}/AddUsers_action.do">
			<div class="table">
			<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
  <tbody>
	<tr>
      	<td style="vertical-align: top;">User Name*<br>
      	</td>
      	<td style="vertical-align: top; text-align:center;">
	     		<input type="text" name = "uname" value=""><br>
	    </td>
    </tr> <tr>
      	<td style="vertical-align: top;">First Name*<br>
      	</td>
      	<td style="vertical-align: top; text-align:center;">
	     		<input type="text" name = "fname" value=""><br>
	    </td>
    </tr> <tr>
     	<td style="vertical-align:top;">Last Name*<br>
      	</td>
      	<td style="vertical-align: top; text-align:center;">
	    		<input type="text" name = "lname" value=""><br>
	    </td>
    </tr> <tr>
      	<td style="vertical-align: top;">Email<br>
      	</td>
      	<td style="vertical-align: top; text-align:center;">
	    		<input type="text" name = "email" value=""><br>
	    </td>
    </tr> <tr>
      	<td style="vertical-align: top;">Password*<br>
      	</td>
      	<td style="vertical-align: top; text-align:center;">
	    		<input type="text" name = "password" value=""><br>
	    </td>
    </tr>
</table>
</div>
	<input name="Submit" class="btnlogout" style="margin-right:45%" value="Add User" type="submit"><br>
</form>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
		<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
		<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
		<link rel="stylesheet" type="text/css" href="Admin.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Overall Commission Report</title>
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
		
	  <li class="dropdown">
		<a href="${basePath}/OverallComm.do">View Overall Commission Report</a>
  
	  <li class="dropdown">
		<a href="${basePath}/SalesReport.do">View Sales Summary Report</a>
	   
	   
	  <li class="dropdown">
	    <a href="${basePath}/ViewUsers.do" class="dropbtn">View Members and add Member</a>
	  </li>
	  
	  <li class="dropdown", style="float:bottom">
	  	<form method="post" action="${basePath}/Logout_action.do">
			<input class = "btnlogout" name="Submit" value="Logout" type="submit"><br>
		</form>
	  </li>
	</ul>
<h1>Overall Commission Report</h1>
<div class="table">
<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
  <tbody>
    <tr>
      	<td style="vertical-align: top;"><b>UserID</b><br> 
      	</td>
      	<td style="vertical-align: top;"><b>UserName</b><br> 
      	</td>
      	<td style="vertical-align: top;"><b>First Name</b><br>
      	</td>
     	 <td style="vertical-align:top;"><b>Last Name</b><br>
      	</td>
      	<td style="vertical-align: top;"><b>Email</b><br>
      	</td>
      	<td style="vertical-align: top;"><b>Seller Rating</b><br>
      	</td>
      	<td style="vertical-align: top;"><b>Commissions</b><br>
      	</td>
   </tr>


	<c:forEach var="item" items="${overallComm}">
	<tr>
     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			 <c:out value="${item.userID}"/>
     	</td>
     	
     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.user_name}"/>
     	</td>
     	
     	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.first_name}"/>
     	</td>
     	
    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.last_name}"/>
    	</td>
    	
    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.email}"/>
    	</td>

    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.seller_rating}"/>
    	</td>
    	
    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
			<c:out value="${item.commissions}"/>
    	</td>
		
    </tr>
	</c:forEach>

   </tbody>
 </table>
 </div>
	<h2>Total Income : $${total}</h2>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
		<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
		<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
		<link rel="stylesheet" type="text/css" href="Admin.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sales Summary Report</title>
</head>
	
	<body>
	   	<div id="header">
			<div class="logo">
				<img src="../Images/HaramBaseICON.png">
			</div>			
	    </div>
	<ul>
	<li class="active">
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
<h1>Sales Summary Report</h1>
<div class="table">
<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
  <tbody>
    <tr>
      	<td style="vertical-align: top;"><b>Category</b><br> 
      	</td>
      	<td style="vertical-align: top;"><b>ItemID</b><br> 
      	</td>
      	<td style="vertical-align: top;"><b>Item Name</b><br>
      	</td>
     	 <td style="vertical-align:top;"><b>Final Selling price</b><br>
      	</td>
      	<td style="vertical-align: top;"><b>Commission</b><br>
      	</td>
   </tr>
	<c:forEach items="${sumItemCategory}" var="itemCate">
		<tr>
   	     	<td style="vertical-align: top; text-align:center;" contenteditable='false' bgcolor= "#babdb6 ">
   	     		<b><c:out value="${itemCate}"/></b>
   	     	</td>
   	    </tr>
		<c:forEach var="i" begin="1" end="${countMap[itemCate]}">
		<c:forEach var="item" items="${sales}">
			<c:if test="${itemCate == item.itemCategory}">
			<%--<c:forEach var="i"  begin="1" end="${countMap[itemCate]}">--%>
			${i + 1}
			<tr>
				<td></td>
				<td style="vertical-align: top; text-align:center;" contenteditable='false'>
					<c:out value="${item.itemID}"/>
				</td>

				<td style="vertical-align: top; text-align:center;" contenteditable='false'>
					<c:out value="${item.itemName}"/>
				</td>

				<td style="vertical-align: top; text-align:center;" contenteditable='false'>
					<c:out value="${item.final_selling_price}"/>
				</td>

				<td style="vertical-align: top; text-align:center;" contenteditable='false'>
					<c:out value="${item.commission}"/>
				</td>
				</tr>
			<tr>
			<c:if test="${i == countMap[itemCate]}">
			<td>SUBTOTAL</td>
   		    <td></td>
   		    <td></td>
			<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<b><c:out value="${totalMap[itemCate]}"/></b>
			</td>
   	    	<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<b><c:out value="${commMap[itemCate]}"/></b>
   	    	</td>
			</tr>
			</c:if>
			</c:if>

		</c:forEach>
		</c:forEach>
	</c:forEach>
	<tr>
		<td><b>TOTAL</b></td>
		<td></td>
		<td></td>
		<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<b><c:out value="${totalMap.sumTotal}"/></b>
		</td>
		<td style="vertical-align: top; text-align:center;" contenteditable='false'>
				<b><c:out value="${commMap.sumComm}"/></b>
		</td>
	</tr>
  </tbody>
</table>
</div>
</body>
</html>
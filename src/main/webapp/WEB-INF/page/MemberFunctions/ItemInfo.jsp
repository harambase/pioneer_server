<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<link rel="stylesheet" type="text/css" href="Member.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Item Information</title>
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
<h1>Item Information</h1>

<div id="table">
<table style="text-align: center; width: 65%; margin-left: auto; margin-right: auto; border: 1px solid;">
  <tbody>
	<tr>
		<td style="vertical-align: top; text-align:left;"><b>Item ID</b><br> 
      	</td>
     	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
     		${item.itemid}
     	</td>
    </tr><tr>
    	<td style="vertical-align: top; text-align:left;"><b>Item Name</b><br> 
      	</td>
     	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			${item.itemname}
     	</td>
    </tr><tr>
   		 <td style="vertical-align: top; text-align:left;"><b>Category</b><br> 
      	</td>	
     	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			${item.itemcategory}
     	</td>
    </tr><tr>
   		 <td style="vertical-align: top; text-align:left;"><b>Start Price</b><br> 
      	</td>
    	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			$${item.startprice}
    	</td>
    </tr><tr>
   		 <td style="vertical-align: top; text-align:left;"><b>Auction starts</b><br> 
      	</td>
    	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			${item.auctionstarttime}
    	</td>
    </tr><tr>
    	<td style="vertical-align: top; text-align:left;"><b>Auction ends</b><br> 
    	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			${item.auctionendtime}
    	</td>
	</tr><tr>
    	<td style="vertical-align: top; text-align:left;"><b>Description</b><br> 
    	<td style="vertical-align: top; text-align:left;" contenteditable='false'>
			${item.description}
    	</td>
	</tr>
   </tbody>
 </table>
</div>
<button style="margin-left: 15%;" onclick="window.history.go(-1);">
	Return
</button>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	Double bidmax = null, bidmin = null;
%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../../../Harambase.css">
	<link rel="stylesheet" type="text/css" href="../../../DropdownMenu.css">
	<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
	<title>Search</title>
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
	   <%--<% search.setSearched(false);%>--%>
<h1>Search an Item</h1>

<div id="container" style="width:90%; margin-left:18%;">
	<div id="search" style="width:420px; margin-right:25%; position:relative; display: inline; float:right; background-color: #d3d7cf;">
	<h2 style="margin:0;">The Harambase &copy; supports the following search capabilities:<br></h2>
		<p style="margin:5%;">
			1. Search by item id alone,<br>
			2. Search by keyword alone,<br>
			3. Search by keyword and category,<br>
			4. Search by keyword and current bid range,<br>
			5. Search by keyword and auction time period,<br>
			6. Search by keyword, category and current bid range,<br>
			7. Search by keyword, category and auction time period,<br>
			8. Search by keyword, current bid range and auction time period,<br>
			9. Search by keyword, category, current bid range and auction time period.<br>
			10. Inexact search on item name (i.e., Soundex)<br>
		</p>
	</div>
	<div style="margin-left:18%; top:0%; display: inline; position:relative;">
		<form method="post" style="margin-left: -32%" action="${basePath}/SearchResult.do">
		<table style="text-align: center; width: 26%; margin-left: auto; margin-right: auto; border: 1px solid;">
		  <tbody>
			<tr>
				<td style="vertical-align: middle;"><b>Item ID</b><br>
				</td>
				<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
					<input name = "itemID"  value = "">
				</td>
			</tr>
			<tr>
				<td style="vertical-align: middle;"><b>Keyword</b><br>
				</td>
				<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
					<input name = "keyword"  value = "">
				</td>
			</tr>
			<tr>
				<td style="vertical-align: middle;"><b>Category</b><br>
				</td>
				<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
					<input name = "category"  value = "">
				</td>
			</tr>
			<tr>
				<td style="vertical-align: middle;"><b>Bid Min</b><br>
				</td>
				<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
					<input name = "bidMin" value = "">
				</td>
			</tr>
			<tr>
				<td style="vertical-align: middle;"><b>Bid Max</b><br>
				</td>
				<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
					<input name = "bidMax" value = "">
				</td>
			</tr>
			<tr>
				<td style="vertical-align: middle;"><b>Auction starts</b><br>
				</td>
				<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
				<input type="hidden" name ="TimeStart" value = "00:00:00">
				<select name="StartMonth">
						<option value="">Mon.</option>
						<option value="01">Jan.</option>
						<option value="02">Feb.</option>
						<option value="03">Mar.</option>
						<option value="04">Apr.</option>
						<option value="05">May.</option>
						<option value="06">Jun.</option>
						<option value="07">Jul.</option>
						<option value="08">Aug.</option>
						<option value="09">Sep.</option>
						<option value="10">Oct.</option>
						<option value="11">Nov.</option>
						<option value="12">Dec.</option>
				</select>

				<select name="StartDay" id = "Day"></select>
					<script>
						(function() { // don't leak
							var elm = document.getElementById("Day"), // get the select
								df = document.createDocumentFragment(); // create a document fragment to hold the options while we create them
							for(var i = 0; i <= 31; i++){
								var option = document.createElement('option'); // create the option element
                                if(i == 0) {
                                    option.value = "";
                                    option.appendChild(document.createTextNode("Day."));
                                }
                                else if(i<10){
									option.value = "0" + i;
									option.appendChild(document.createTextNode("0"+i));
								}
								else{
									option.value = i;
									option.appendChild(document.createTextNode(i));
								}// set the textContent in a safe way.
								df.appendChild(option); // append the option to the document fragment
							}
							elm.appendChild(df); // append the document fragment to the DOM. this is the better way rather than setting innerHTML a bunch of times (or even once with a long string)
						}());
				</script>

				<select name="StartYear" id = "Year"></select>
				<script>
					(function() { // don't leak
						var elm = document.getElementById("Year"), // get the select
							df = document.createDocumentFragment(); // create a document fragment to hold the options while we create them
						for(var i = 0; i <= 2020; i++){
                            var option = document.createElement('option'); // create the option element
                            if(i == 0) {
                                option.value = "";
                                option.appendChild(document.createTextNode("Year."));
                                i += 2017;
                            }
                            else
                                option.appendChild(document.createTextNode(i));

                            df.appendChild(option); // append the option to the document fragment

                        }
						elm.appendChild(df); // append the document fragment to the DOM. this is the better way rather than setting innerHTML a bunch of times (or even once with a long string)
					}());
				</script>
				</select>
				</td>
			</tr>
			<tr>
				<td style="vertical-align: middle;"><b>Auction Ends</b><br>
				</td>
				<td style="vertical-align: middle; text-align:left;" contenteditable='false'>
				<input type="hidden" name ="TimeEnd" value = "00:00:00">
				<select name="EndMonth">
						<option value="">Mon.</option>
						<option value="01">Jan.</option>
						<option value="02">Feb.</option>
						<option value="03">Mar.</option>
						<option value="04">Apr.</option>
						<option value="05">May.</option>
						<option value="06">Jun.</option>
						<option value="07">Jul.</option>
						<option value="08">Aug.</option>
						<option value="09">Sep.</option>
						<option value="10">Oct.</option>
						<option value="11">Nov.</option>
						<option value="12">Dec.</option>
				</select>

				<select name="EndDay" id = "Day2"></select>
					<script>
						(function() { // don't leak
							var elm = document.getElementById("Day2"), // get the select
								df = document.createDocumentFragment(); // create a document fragment to hold the options while we create them
							for(var i = 0; i <= 31; i++){
								var option = document.createElement('option'); // create the option element
                                if(i == 0) {
                                    option.value = "";
                                    option.appendChild(document.createTextNode("Day."));
                                }
								else if(i<10){
									option.value = "0" + i;
									option.appendChild(document.createTextNode("0" + i));
								}
								else{
									option.value = i;
									option.appendChild(document.createTextNode(i));
								}// set the textContent in a safe way.
								df.appendChild(option); // append the option to the document fragment
							}
							elm.appendChild(df); // append the document fragment to the DOM. this is the better way rather than setting innerHTML a bunch of times (or even once with a long string)
						}());
				</script>

				<select name="EndYear" id = "Year2"></select>
				<script>
					(function() { // don't leak
						var elm = document.getElementById("Year2"), // get the select
							df = document.createDocumentFragment(); // create a document fragment to hold the options while we create them
						for(var i = 0; i <= 2020; i++){
                            var option = document.createElement('option'); // create the option element
                            if(i == 0) {
                                option.value = "";
                                option.appendChild(document.createTextNode("Year."));
                                i += 2017;
                            }
                            else
								option.appendChild(document.createTextNode(i));
								// append the option to the document fragment
                            df.appendChild(option);
                        }
						elm.appendChild(df); // append the document fragment to the DOM. this is the better way rather than setting innerHTML a bunch of times (or even once with a long string)
					}());
				</script>
				</td>
			</tr>
		   </tbody>
		 </table>
		<input name="Submit" class = "btnsearch2" style = "margin-right: -11%" value="Search" type="submit">
		</form>
	</div>
</div>
</body>
</html>
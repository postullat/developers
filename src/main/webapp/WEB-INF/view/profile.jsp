<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">

	<%@include file="/WEB-INF/view/part/links.jsp" %>
	<script type="text/javascript" src="resources/js/page/profile.js" charset="utf-8"></script>
	<link href="resources/css/page/profile.css" rel="Stylesheet" type="text/css">

    <title>Developers - Profile</title>
   
</head>

<body>
	<!-- header -->
	<%@include file="/WEB-INF/view/part/header.jsp" %>
	<!--end header -->
    <!-- main -->
    <div id="main">
    	<div id="content">
    		<div id="sidebar">
			    	<div id="sidebar_top"></div>
			        <div id="sidebar_text">
			        	<img id="userPhotoImg" src="#" >
			        </div>
			        <div id="sidebar_bottom"></div>
	    	</div>
	    		
	    	<div id="text">
	    	
	    		<!-- інформація про користувача -->
	    		
	    		<form id="findUser"  method="post">
	    			<input type="text" id="user_name" name="user_name" value="">
	    			<input class="login_button" type="submit" id="s_Find" name="s_Find" value="Find User by nick">	    		
	    		</form>
	    		
				<div id="userInfo">
					<table class = "userInfoTableClass">
						<tr>
						<th><h1>User:</h1></th>
						</tr>
						<tr> 
							<td> Nickname:&nbsp; </td>  
							<td> <span id="userName" class ="userStatValue" ></span> </td>
						</tr>
						<tr>
							<td> E-Mail:&nbsp; </td>
							<td><span id="userEmail"  class ="userStatValue"></span> </td>
						</tr>
					</table>
				</div>
	    	
		    	<div id="userStats">
					<table class = "userInfoTableClass">
					<tr>
					<th><h1> User Statistics:</h1> </th>
					</tr>
					<tr> 
						<td> Score:&nbsp; </td>
						<td><span id="userScore" class ="userStatValue" ></span><br> </td>
					</tr>
					<tr>
						<td>Winnings:&nbsp;</td>
						<td><span id="userWinnings" class ="userStatValue" ></span><br></td>
					</tr>
					<tr>
						<td>Losings:&nbsp;</td>
						<td><span id="userLosings"  class ="userStatValue"></span></td>
					</tr>
					</table>
				</div>
	    		
	    		
	    		
	    		
	    		<div id="divFormUploadContainer">
					<div id="divFormUpload">
						
			    		<div id="sidebar">
					    	<div id="sidebar_top"></div>
					        <div id="sidebar_text">
							    <form id="formUpload" enctype="multipart/form-data" method="post" action="file-upload">
									<b>Select a new photo:</b><br>
									<input id="file" type="file" size="15" name="file" ><br><br>
									<input id="button" type="submit" value="Send" name="button">
								</form>
					        </div>
					        <div id="sidebar_bottom"></div>
			    		</div>
	    		
	    			</div>
				</div>
	    		
	    		
		    	
	    	<!-- end text -->
	    	</div>
	   <!-- end context -->
	   </div> 
	    
 	

	<!-- footer -->
    	<%@include file="/WEB-INF/view/part/footer.jsp"%>
    <!-- end footer -->
    	</div>
    <!-- end main -->

</body>
</html>
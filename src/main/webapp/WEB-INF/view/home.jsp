<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">

	<%@include file="/WEB-INF/view/part/links.jsp" %>

    <title>Developers - Home</title>
   
</head>

<body >
	<!-- header -->
		<%@include file="/WEB-INF/view/part/header.jsp"%>
    <!--end header -->
    <!-- main -->
    	<div id="main" align="middle">
	    	<div id="content">
	    		<div id="sidebar">
			    	<div id="sidebar_top"></div>
			        <div id="sidebar_text">
			        	<h3>News</h3>
			        	<p>- Update design</p>
			        	<p>- Added page Home</p>
			        	<p>- Added page Profile</p>
			        	<p>- Added page Connect</p>
			        	<p>- Added page Game</p>
			        	<p>- Added page About</p>
			        	<p>- Project completed :)</p>
			        </div>
			        <div id="sidebar_bottom"></div>
	    		</div>

	    		<div id="text">
	    			<%@include file="/WEB-INF/view/part/logregforms.jsp" %>
	    		</div>
				<p style="height:35px;"></p>
				<p class="welcome">Welcome</p>
				<p class="main_text">
				<br>While (!Life.EOF) { 
				<br>You.Money++; 
				<br>You.Girls.Add(new Girl(90,60,90)); 
				<br>BeHappy(); 
				<br>}
				</p>
    		</div>
    		
    <!-- footer -->
    	<%@include file="/WEB-INF/view/part/footer.jsp"%>
    <!-- end footer -->
    	</div>
    <!-- end main -->
</body>
</html>
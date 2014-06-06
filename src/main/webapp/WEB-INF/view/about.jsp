<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">

	<%@include file="/WEB-INF/view/part/links.jsp" %>
	    <link href="resources/css/page/about.css" rel="Stylesheet" type="text/css">
    
    <title>Developers - About</title>
   
</head>

<body>
	<!-- header -->
		<%@include file="/WEB-INF/view/part/header.jsp"%>
    <!--end header -->
    <!-- main -->
    	<div id="main">
	    	<div id="content">
	    		
	    			<h1><strong>About game</strong></h1>
	    			<%@include file="/WEB-INF/view/part/logregforms.jsp" %>
	 
<p>The game is a combination of simple arcade and strategy.
After initializing cards there are two teams. Teams are a group of programmers.
They write a draft. Also in the game there are such objects as coffee machines, toilets, servers, computers, switches and other supplies.
Team competitors can break objects other team.
This will reduce the speed of writing the command of the project.
So each team has to write code and break objects opposing team.
Wins team that will complete your project faster.
Essence of the game and control are intuitive
<p><br>"How to get developer" doesn't require HDD installation and can be run on any Internet-connected PC.
Only real users plays each other on 2D maps. Each team in the game is driven by real people.
The game is fun and exciting and we hope it enjoy you.</p>
	    	
    		</div>
    <!-- footer -->
    	<%@include file="/WEB-INF/view/part/footer.jsp"%>
    <!-- end footer -->
    	</div>
    <!-- end main -->
</body>
</html>
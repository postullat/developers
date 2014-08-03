<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@include file="/WEB-INF/view/part/links.jsp"%>
<script type="text/javascript" src="resources/js/page/connect.js"
	charset="utf-8"></script>
<link href="resources/css/page/connect.css" rel="Stylesheet"
	type="text/css">

<title>Developers - Connect</title>

</head>
<body>
	<!-- header -->
		<%@include file="/WEB-INF/view/part/header.jsp"%>
    <!--end header -->
    <!-- main -->
    	<div id="main">
	    	<div id="content">
	    		<div id="sidebar">
			        <div id="sidebar_text">
			        	<h2>Users online - ${usersOnline.size()}</h2>
			        	<c:forEach  var="user" items="${usersOnline}" > 
			        	<div class="activeUser">${user}</div>
			        	</c:forEach>
			        </div>
	    		</div>
	    		<div id="text">
	    			<div id="listOfGame">
	    				<h1><strong>List of games:</strong></h1>
						<div id="divTableGames">
							<table id="tableGames" cellpadding="5px">

							</table>
						</div>
					</div>
					<div id="createGame" class="buttonConn">Create game</div>
	    		</div>
    		</div>
    <!-- footer -->
    	<%@include file="/WEB-INF/view/part/footer.jsp"%>
    <!-- end footer -->
    	</div>
    <!-- end main -->
</body>
</html>
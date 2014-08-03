<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set value="blabla" var="userRole" />
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>


<div id="header">
	<div id="logo">
		<a href="#">How to annoy developer</a>
	</div>
	<div id="menu">
		<ul>
		
		<security:authorize ifNotGranted="ROLE_USER, ROLE_PLAYER, ROLE_ADMIN">
			<li><a id="btnHome" href="home">Home</a></li>
			<li><a id="btnAbout" href="about">About</a></li>
		</security:authorize>
		
		<security:authorize ifAnyGranted="ROLE_USER, ROLE_PLAYER, ROLE_ADMIN">
			<security:authentication property="principal.user.role" var="roles" scope="page"/>
			<security:authentication property="principal.user.name" var="userName" scope="page"/>
		
		<li><a id="btnConnect" href="connect">Home</a></li>
		<li><a id="btnUserName" href="#">Logged in as <u>${userName}</u>, role - ${fn:substring(roles,5,fn:length(roles))} </a></li>
			<li><a id="btnProfile" href="profile">My profile</a></li>
			<li><a id="btnLogout" onclick="document.getElementById('logoutForm').submit()">Logout</a></li>
		</security:authorize>
			
			
		</ul>
	</div>
</div>

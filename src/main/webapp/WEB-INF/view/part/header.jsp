<!-- header -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<div id="header">
	<div id="logo">
		<a href="#">How to annoy developer</a>
	</div>
	<div id="menu">
		<ul>
			<li><a id="btnHome" href="#">Home</a></li>
			<li><a id="btnProfile" href="#">Profile</a></li>
			<li><a id="btnAbout" href="#">About</a></li>
			<li><a id="btnRegist" href="#">Register</a></li>

			<security:authorize ifAnyGranted="ROLE_USER, ROLE_PLAYER, ROLE_ADMIN">
				<li><a id="btnLogout" onclick="document.getElementById('logoutForm').submit()">Logout</a></li>
			</security:authorize>
			<li><a id="countUserOnline" href="#"></a>
				<ul id="listOfcountUserOnline">

				</ul></li>
		</ul>
	</div>
</div>

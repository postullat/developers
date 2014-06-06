<%@ page language="java" contentType="text/html; charset=windows-1251"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1251">

<%@include file="/WEB-INF/view/part/links.jsp"%>
<script type="text/javascript"
	src="resources/js/page/game/menu-toggle.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/js/page/game.js"
	charset="utf-8"></script>
<script type="text/javascript" src="resources/js/page/game/players.js"
	charset="utf-8"></script>
<script type="text/javascript" src="resources/js/page/game/gamename.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="resources/js/page/game/mouse-click.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/js/page/game/unit.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="resources/js/page/game/status-bar.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/js/page/game/chat.js"
	charset="utf-8"></script>
<script type="text/javascript" src="resources/js/page/game/finishgame.js"
	charset="utf-8"></script>

<script type="text/javascript"
	src="resources/js/lib/jquery.dragndrop.js"></script>
<script type="text/javascript"
	src="resources/js/lib/jquery-ui-1.8.20.custom.min.js"></script>
<script type="text/javascript" src="resources/js/lib/moment.js"></script>


<script type="text/javascript"
	src="resources/js/lib/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="resources/js/lib/jquery.ui.progressbar.js"></script>

<link href="resources/css/page/progressbar/jquery.ui.core.css" rel="Stylesheet"
	type="text/css">
<link href="resources/css/page/progressbar/jquery.ui.progressbar.css" rel="Stylesheet"
	type="text/css">
<link href="resources/css/page/progressbar/jquery.ui.theme.css" rel="Stylesheet"
	type="text/css">


<link href="resources/css/page/game.css" rel="Stylesheet"
	type="text/css">

<title>Developers - Game</title>

</head>

<body onload="get_MapObject();">
	<!-- header -->
	<div id="gameHeader">
		<div id="logo">
			<a href="#">How to annoy developer</a>
		</div>
		<div id="menu">
			<ul>
				<li><a id="btnHome" href="#">Home</a></li>
				<li><a id="btnProfile" href="#">Profile</a></li>
				<li><a id="btnAbout" href="#">About</a></li>
				<li><a id="btnExit" href="#">Exit</a></li>
				<li><a id="btnLogin" href="#">Login</a></li>
				<li><a id="btnRegist" href="#">Register</a></li>
				<li><a id="btnLogout" href="#">Logout</a></li>
				<li><a id="countUserOnline" href="#"></a>
					<ul id="listOfcountUserOnline">

					</ul></li>
			</ul>
		</div>
		<div id="divMenuToggler">
			<div id="menuToggler"></div>
		</div>
	</div>
	<!--end header -->
	<!-- main -->

	<div id="gameMain">
		<div id="gameContent">
			<div id="gameName"></div>
			<div id="divPlayers">
				<div id="divPlayerYou">
					
				</div>
				<div id="divOpponents"></div>
			</div>
			<div id="divCanvas">
				<canvas id="map_canvas"></canvas>

				<div id="divOnCanvas">
					<canvas id="unit_canvas"></canvas>
				</div>
				<div id="timerWaiting"></div>
				<div id="activeObjectMenu"></div>
			</div>

			<div id="unitOptions">
				<div id="divUnitImg"></div>
				<div id="unitInfo"></div>
			</div>
			<div id="gameInfo">
				<div id="divAllUnitsStatusBar"></div>
			</div>
			<div id="div_chat" class="block">
				Chat
				<form id="chatForm" name="chatForm" method="post">

					<p>
					<div id="output_chat" name="output_chat">time - user: message</div>
					</p>
					<p>
						<textarea id="input_chat" placeholder="Input your message"
							name="input_chat" cols="30" rows="1" contentEditable="false"></textarea>
					</p>
					<p>
						<input type="submit" id="s_Send" name="s_Send" value="Send"
							width="150">

					</p>
				</form>
			</div>
		</div>
		<!-- footer -->
		<%@include file="/WEB-INF/view/part/footer.jsp"%>
		<!-- end footer -->
	</div>
	<!-- end main -->

</body>
</html>
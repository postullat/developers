var youName = "";
var oponentName = "";

$(function() {

	var divOpponents = $("#divOpponents");
	var divPlayerYou = $("#divPlayerYou");
	var maxCodeLinesWidth = 250;

	var listOfPlayers = function() {
		$
				.ajax({
					url : "get-game-data",
					contentType : "application/x-www-form-urlencoded",
					data : {
						"message" : "get_players"
					},
					success : function(youOpponentsMaxCL) {
						var you = youOpponentsMaxCL[0];
						var opponents = youOpponentsMaxCL[1];
						var maxCodeLines = youOpponentsMaxCL[2];

						youName = you.name;

						var myCodeLinesWidth = maxCodeLinesWidth
								* you.codeLines / maxCodeLines + 1;
						divPlayerYou
								.html("<div class=\"divYou\">"
										+ "<div class=\"playerYou\">"
										+ "<img id=\"youPlayerImg\" src=\"resources/img/user/"
										+ you.info.photo
										+ "\"/><br>"
										+ you.name
										+ "</div>"
										+ "<div class=\"playerYouInfo\"><div class=\"divMaxCodeLines\">"
										+ "<div class=\"gameStatusBar\" style=\"width: "
										+ myCodeLinesWidth
										+ "px\"></div></div>" + "</div>"
										+ "</div>");
						
						divOpponents.html("");
						for ( var i = 0; i < opponents.length; ++i) {
							var codeLines = opponents[i].codeLines;
							var opponentCodeLinesWidth = maxCodeLinesWidth
									* codeLines / maxCodeLines + 1;
							divOpponents
									.append("<div class=\"divPlayerOpponents\">"
											+ "<div class=\"playerOpponent\">"
											+ "<img class=\"opponentPlayerImg\" src=\"resources/img/user/"
											+ opponents[i].info.photo
											+ "\"/><br>"
											+ opponents[i].name
											+ "</div>"
											+ "<div class=\"playerOpponentInfo\"><div class=\"divMaxCodeLines\">"
											+ "<div class=\"gameStatusBar\" style=\"width: "
											+ opponentCodeLinesWidth
											+ "px\"></div></div>"
											+ "</div>"
											+ "</div>");
						}
					}
				});
	};

	var timerWaiting = function(){
		$.ajax({
			url:"timer-waiting",
			success: function(isRunningGame) {
				
				
				if (isRunningGame){
					
						$("#timerWaiting").hide();

				}else{ 
					$("#timerWaiting").html("<img  class=\"timerImg\" src=\"resources/img/timer2.gif\"/>");
				}	
			}
		});
	};

 	var timerListOfPlayers = setInterval(function() {
		listOfPlayers();
	}, 60);

	var timerTimerWaiting = setInterval(function() {
		timerWaiting();
	}, 1000);

	listOfPlayers();
	timerWaiting();
});
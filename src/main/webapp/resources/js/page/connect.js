$(function() {

	var createGame = $("#createGame");
	var buttonJoin;
	var divTableGames = $("#divTableGames");
	var tableGames = $("#tableGames");
	var sidebar_text = $("#sidebar_text");

	var joinFunction = function(element) {
		$.ajax({
			url : "create-game",
			contentType : "application/x-www-form-urlencoded",
			data : {
				"message" : "join_to_game",
				"gameName" : element.id
			},
			success : function(data) {
				location.replace("/developers/game");
			},
			error : function(error) {
				alert(error.responseText);
			}
		});
	};
	
	var showGameInfo = function(element) {
		$.ajax({
			url : "get-game-data",
			contentType : "application/x-www-form-urlencoded",
			data : {
				"message" : "get_game_info",
				"gameName" : element.find(".gameNameCell").html()
			},
			success : function(gameInfo) {
				sidebar_text.html("<center><h1>Game info</h1></center><br>");
				sidebar_text.append("<center><h2>" + gameInfo.name + "</h2></center><br>");
				sidebar_text.append("<center><img width=\"200px\" src=\"resources/img/user/" + gameInfo.creator.info.photo + "\"></center><br>");
				sidebar_text.append("<center>" + gameInfo.creator.name + "</center>");
			},
			error : function(error) {
				alert(error.responseText);
			}
		});
	};

	/* виводить список створених неактивних ігор */
	var getListOfGames = function() {
		$
				.ajax({
					url : "list-of-games",
					success : function(list) {
						if (list.length != 0) {
							/* вивід всіх ігор у табличці */
							tableGames.html("<tr bgcolor=\"#0d1114\">"
									+ "<th>Game name</th>"
									+ "<th>Time of creation</th>"
									+ "<th>Creator</th>"
									+ "<th width=\"115px\">Join game</th>"
									+ "</tr>");
							for ( var i = 0; i < list.length; ++i) {
								var joinString = "<tr><td class=\"gameNameCell\">" + list[i].name
										+ "</td><td>" + list[i].dateOfCreation
										+ "</td><td>" + list[i].creator.name
										+ "</td>";
								if (list[i].isJoinAvailable) {
									joinString += "<td><div id='"
											+ list[i].name
											+ "' class='buttonJoin' >Join to game</div></td></tr>";
								} else {
									joinString += "<td></td></tr>";
								}
								tableGames.append(joinString);
							}
							$("#tableGames tr").click(function() {
								showGameInfo($(this));
							});
							buttonJoin = $(".buttonJoin");
							buttonJoin.click(function() {
								joinFunction(this);
							});
						} else {
							tableGames.html("None of games isn't created");
						}

					}
				});
	};

	createGame.click(function() {
		$.ajax({
			url : "create-game",
			contentType : "application/x-www-form-urlencoded",
			data : {
				"message" : "create_new_game"
			},
			success : function(data) {
				location.replace("/developers/game");
			},
			error : function(error) {
				alert(error.responseText);
			}
		});
	});

	// оновляє список ігор
	var listOfGamesTimer = setInterval(function() {
		getListOfGames();
	}, 1000);

	getListOfGames();

});
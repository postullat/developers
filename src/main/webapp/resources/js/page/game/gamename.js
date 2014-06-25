$(function() {
	
	var gameName = $("#gameName");
	
	var getGameName = function() {
		$.ajax({
			url:"get-game-data",
			contentType: "application/x-www-form-urlencoded",
			data: {
			"command" : "get_game_name",
			"gameName" : "unknown"},
			success: function(name){
				gameName.html(name);
			}
		});
	};
	
	getGameName();
	
});
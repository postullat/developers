$(function() {
	
	var gameName = $("#gameName");
	
	var getGameName = function() {
		$.ajax({
			url:"get-game-data",
			contentType: "application/x-www-form-urlencoded",
			data: {"message" : "get_game_name"},
			success: function(name){
				gameName.html(name);
			}
		});
	};
	
	getGameName();
	
});
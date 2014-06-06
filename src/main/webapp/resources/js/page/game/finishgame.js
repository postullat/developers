$(function() {

	var userWin = "";
	var userLoose = "";
	var flag = false;
	
	var getWinner = function() {				//функція для отримання тексту всього чату
		$.ajax({
			url: "finish-game",					//звертаємось до сервлет, який має адресу "developers/chat"
			data: {"what":"getWinLoose"},
			success: function(gameInfo) {		//отримуємо відповідь
				
				userWin = gameInfo.userWin;
				userLoose = gameInfo.userLoose;
				if(userWin!= null && userLoose!= null && flag == false){
					alert("Win - "+userWin+" Loose - "+userLoose);
					flag = true;
				}
					
			}
			});
	}
	
	function init_finish_game(){
		setInterval(getWinner,4500);			//запускаєм опитування чату на сервері кожні 2 секунди
	}
	
	init_finish_game();
});
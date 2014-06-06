$(function() {

	var userWin = "";
	var userLoose = "";
	var flag = false;
	
	var getWinner = function() {				//������� ��� ��������� ������ ������ ����
		$.ajax({
			url: "finish-game",					//���������� �� �������, ���� �� ������ "developers/chat"
			data: {"what":"getWinLoose"},
			success: function(gameInfo) {		//�������� �������
				
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
		setInterval(getWinner,4500);			//�������� ���������� ���� �� ������ ���� 2 �������
	}
	
	init_finish_game();
});
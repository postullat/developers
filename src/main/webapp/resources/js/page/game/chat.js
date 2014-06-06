$(function() {
	

	var s_Send = $("#s_Send");				//�������� Send/ ���. ��� ��������� ����������� �� �������
	var output_chat = $("#output_chat");	//textarea ��� ����� ����������� � ���
	var div_chat = $("#div_chat");
	var you = $("#divPlayerYou");
	
	var greenColor = "<font color='#BB2FD4'>";
	var blueColor = "<font color='#3399ff'>";
	var blackColor = "<font color='#000000'>";
	var endColor = "</font>";
	
	var lastDate = 0;
	var sendDate = 0;
	var new_time;
	
//	var sound = $("#audio1")[0];
//	var on = $("#on");
//	var off = $("#off");
//	
//	off.hide();

		
	s_Send.click(function() {				//�������� ����������� ��� ���� �� ������ Send
		
		var input_chat = $("#input_chat");	//������� ����� � ���� �����	
		//alert(youName+" "+oponentName);
		if(input_chat.val()!=""){			//���� ���� ����� �� ����� - �� �������� �����
			
			$.ajax({
				url: "chat",					//�������� �� �������, ���� �� ������ "developers/chat"
				contentType: "application/x-www-form-urlencoded",
				data: {"what":"write","message":input_chat.val()},
				success: function(chat) {
				
					input_chat.val("");			//���� ���������� ����������� ������� ���� �����
				}

			});			
		}

		return false;						//��� �� ������������������� �������
	});
	
	
	var getChat = function() {				//������� ��� ��������� ������ ������ ����
		$.ajax({
			url: "chat",					//���������� �� �������, ���� �� ������ "developers/chat"
			contentType: "application/x-www-form-urlencoded",
			data: {"what":"read","message":"get_me_chat_pls"},
			success: function(chat) {		//�������� �������
			
				var len = chat.length;		//��������� ������� ������
				var text="";				//������� ���� ����
				for(var i=0; i<len; i++){	//��������� ������ ����� ����������
					
					
					sendDate = chat[i].time*1;	//������������ ������� ����
					new_time = moment(sendDate);
					
							
					var time = new_time.format('hh.mm.ss');
					if(chat[i].user == youName){
						text+=blackColor+time+endColor+" - "+greenColor+"<ins>"+chat[i].user+"</ins>"+": "+chat[i].message+"<br>"+endColor;
					} else{
						text+=blackColor+time+endColor+" - "+blueColor+chat[i].user+": "+chat[i].message+"<br>"+endColor;
					}
					
				}
				
				if(sendDate>lastDate){
//					sound.play();
					lastDate = sendDate;
				}
				output_chat.html(text);		//�������� ������ ����� ���������� � textarea

						}
		});
	}
	
	function init_chat(){
		setInterval(getChat,2200);			//�������� ���������� ���� �� ������ ���� 2 �������
		
	}
	
	 $(document).ready(function(){			//������� ��� ���������� ����
	    	
	    	$(".block").draggable();
	    	$(".block").droppable();
	    	
	    });
	 
	init_chat();
	
});
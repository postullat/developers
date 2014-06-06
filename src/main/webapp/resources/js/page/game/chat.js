$(function() {
	

	var s_Send = $("#s_Send");				//кнопочка Send/ Вик. для відсилання повідомлення на сервлет
	var output_chat = $("#output_chat");	//textarea для вводу повідомлення в чаті
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

		
	s_Send.click(function() {				//відсилаємо повідомлення при кліку на кнопці Send
		
		var input_chat = $("#input_chat");	//зчитуємо текст з поля вводу	
		//alert(youName+" "+oponentName);
		if(input_chat.val()!=""){			//якщо поле вводу не пусте - то відсилаємо текст
			
			$.ajax({
				url: "chat",					//передаємо на сервлет, який має адресу "developers/chat"
				contentType: "application/x-www-form-urlencoded",
				data: {"what":"write","message":input_chat.val()},
				success: function(chat) {
				
					input_chat.val("");			//після надсилання повідомлення очищаємо поле вводу
				}

			});			
		}

		return false;						//щоб не перезавантажувалась сторінка
	});
	
	
	var getChat = function() {				//функція для отримання тексту всього чату
		$.ajax({
			url: "chat",					//звертаємось до сервлет, який має адресу "developers/chat"
			contentType: "application/x-www-form-urlencoded",
			data: {"what":"read","message":"get_me_chat_pls"},
			success: function(chat) {		//отримуємо відповідь
			
				var len = chat.length;		//визначаємо довжину масиву
				var text="";				//очищаємо вікно чату
				for(var i=0; i<len; i++){	//створюємо стрічку нових повідомлень
					
					
					sendDate = chat[i].time*1;	//запамятовуємо останню дату
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
				output_chat.html(text);		//записуємо стрічку нових повідомлень в textarea

						}
		});
	}
	
	function init_chat(){
		setInterval(getChat,2200);			//запускаєм опитування чату на сервері кожні 2 секунди
		
	}
	
	 $(document).ready(function(){			//функція для переміщення чату
	    	
	    	$(".block").draggable();
	    	$(".block").droppable();
	    	
	    });
	 
	init_chat();
	
});
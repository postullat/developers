$(function() {
	
	var formLogin = $("#formLogin");
	var formRegister = $("#formRegister");
	var btnRegister = $("#btnRegist");
	var btnLogin = $("#btnLogin");
	var divFormLogin = $("#divFormLogin");
	var divFormRegister = $("#divFormRegister");
	var btnLogout = $("#btnLogout");
	var registerField = $("#registerField");

	
	
	
	
	
	
	/*запускається на початку, перевіряє чи користувач зареєстрований*/
	var verifyLogin = function() {
		$.ajax({
			type: "POST",
			url: "check-login",
			contentType: "application/x-www-form-urlencoded",
			dataType: "json",
			data: {"userName":"unknown"},
			success: function(user) {
				if (null != user) {
					
					registerField.html("Hello, " + user.name + "!");
					countUser();
				} 
			}
		});
	};

	
	var countUser = function(){
//		$.ajax({
//			url:"count-user-online",
//			success: function(names){
//				countUserOnline.html("users online: " + names.length);
//				listOfcountUserOnline.html("");
//				for(var i=0; i<names.length; ++i){
//					listOfcountUserOnline.append("<li><a href=\"profile?user_name="+names[i] +"\">"+names[i] +"</a></li>");
//					
//				}
//			}
//		});
	};
	
	var init = function() {
		countUser();
		verifyLogin();
	};
	

	/*добавлення коритувача до бази даних*/
	formRegister.submit(function() {
		$.ajax({
			url: "register",
			contentType: "application/x-www-form-urlencoded",
			dataType: "text",
			data: formRegister.serialize(),
			success: function(data) {
				location.reload();
			},
			error: function(error) {
				$("#errorName").html(error.responseText);
			}
		});
		return false;
	});
	
	// оновляє кількість користувачів онлайн
	var countUserTimer = setInterval(function() {
		countUser();
	}, 5000);
	
	init();
		
});

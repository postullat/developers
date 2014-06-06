$(function() {
	
	var formLogin = $("#formLogin");
	var formRegister = $("#formRegister");
	var btnRegister = $("#btnRegist");
	var btnLogin = $("#btnLogin");
	var divFormLogin = $("#divFormLogin");
	var divFormRegister = $("#divFormRegister");
	var btnLogout = $("#btnLogout");
	var registerField = $("#registerField");
	var countUserOnline = $("#countUserOnline");
	var listOfcountUserOnline = $("#listOfcountUserOnline");
	
	var btnProfile = $("#btnProfile");
	
	// by default, link "Profile" is disabled
	var profileLocation = "#";
	
	// enables button "Profile" when user is logined
	var enableBtnProfile = function() {
		profileLocation = "profile";
		btnProfile.css({color : "white"});
	};
	
	// disables button "Profile" when user has logout
	var disableBtnProfile = function() {
		profileLocation = "#";
		btnProfile.css({color : "brown"});
	};
	
	/*запускається на початку, перевіряє чи користувач зареєстрований*/
	var verifyLogin = function() {
		$.ajax({
			url: "check-login",
			success: function(user) {
				if (null != user) {
					btnLogin.hide();
					btnRegister.hide();
					registerField.show();
					registerField.html("Hello, " + user.name + "!");
					enableBtnProfile();
					btnLogout.show();
					countUser();
				} else {
					btnLogout.hide();
					disableBtnProfile();
				}
			}
		});
	};
//виводить список юзерів онлайн	
	var countUser = function(){
		$.ajax({
			url:"count-user-online",
			success: function(names){
				countUserOnline.html("users online: " + names.length);
				listOfcountUserOnline.html("");
				for(var i=0; i<names.length; ++i){
					listOfcountUserOnline.append("<li><a href=\"profile?user_name="+names[i] +"\">"+names[i] +"</a></li>");
					
				}
			}
		});
	};
	
	btnProfile.click(function () {
		location.href = profileLocation;
	});
	
	/*показує форму реєстрації*/
	btnRegister.click(function () {
		divFormRegister.toggle('slow');
	});
	
	/*показує форму логування*/
	btnLogin.click(function () {
		divFormLogin.toggle('slow');
	});
	
	var init = function() {
		countUser();
		verifyLogin();
	};
	
	/*вихід користувача*/
	btnLogout.click(function() {
		$.ajax({
			url: "logout",
			success: function(data) {
				location.href="home";
			}
		});
	});
	
	/*авторизація користувача*/
	formLogin.submit( function(){
		$.ajax({
			url: "login",
			contentType: "application/x-www-form-urlencoded",
			dataType: "text",
			data: formLogin.serialize(),
			success: function(data) {
				divFormLogin.hide();
				verifyLogin();
				location.replace("/developers/connect"); 
			},
			error: function(error) {
				$("#errorLogin").html(error.responseText);
			}
		});
		return false;
	});
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

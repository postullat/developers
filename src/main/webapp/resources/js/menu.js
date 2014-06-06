$(function() {
	
	// buttons
	
	var btnHome = $("#btnHome");
	var btnProfile = $("#btnProfile");
	var btnAbout = $("#btnAbout");
	var btnExit = $("#btnExit");
	var listOfcountUserOnline = $("#listOfcountUserOnline");
	var countUserOnline = $("#countUserOnline");

//	var btnRegister = $("#btnRegist");
//	var btnLogin = ("#btnLogin");
//	var divFormLogin = $("#divFormLogin");
//	var divFormRegister = $("#divFormRegister");

	// events
	countUserOnline.click(function(){
		listOfcountUserOnline.show('slow');
	});
	
	listOfcountUserOnline.hover(function(){
		
		},function(){
		listOfcountUserOnline.hide('slow');
	});
	
	btnHome.click(function() {
		location.href = "home";
	});	

	btnProfile.click(function() {
		location.href = "#";
	});
	
	btnAbout.click(function() {
		location.href = "about";
	});
	
	btnExit.click(function() {
		$.ajax({
			url:"exit-game",
			contentType: "application/x-www-form-urlencoded",
			success: function(players){
				location.replace("home");
			},
			error: function(error) {
				
			}
		});
	});
	
//	btnRegister.click(function () {
//		divFormRegister.toggle('slow');
//	});
//	
//	btnLogin.click(function () {
//		divFormLogin.toggle('slow');
//	});

		
});
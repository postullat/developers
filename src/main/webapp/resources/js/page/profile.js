$(function() {
	
	var body_div = $("#body_div");
	
	var userPhotoImg = $("#userPhotoImg");
	
	var userName = $("#userName");
	var userEmail = $("#userEmail");
	var userScore = $("#userScore");
	var userWinnings = $("#userWinnings");
	var userLosings = $("#userLosings");
	
	var divFormUpload = $("#divFormUpload");
	var formUpload = $("#formUpload");
	
	var formFindUser = $("#findUser");
	
	//відсилаємо запит для пошуку користувача по імені
	formFindUser.click(function() {
		$.ajax({
			url: "check-login",
			contentType: "application/x-www-form-urlencoded",
			data: formFindUser.serialize(),
			success: function(user) {

				userName.html(user.name);
				userEmail.html(user.info.email);
				userScore.html(user.stats.score);
				userWinnings.html(user.stats.winnings);
				userLosings.html(user.stats.losings);
				userPhotoImg.attr("src",  "resources/img/user/" + user.info.photo);
				divFormUpload.dispaly = none;
			}
		});
		return false;
	});
	
	// завантажує дані користувача, який ввійшов на сайт (фото, ім'я, статистику, і т.д.)
	var loadProfile = function() {
		$.ajax({
			url: "check-login",
			success: function(user) {
				userName.html(user.name);
				userEmail.html(user.info.email);
				userScore.html(user.stats.score);
				userWinnings.html(user.stats.winnings);
				userLosings.html(user.stats.losings);
				userPhotoImg.attr("src",  "resources/img/user/" + user.info.photo);
			}
		});
	};
	
	divFormUpload.click(function(e) {
		e.stopPropagation();
	});
	
	userPhotoImg.click(function(e) {
		e.stopPropagation();
		divFormUpload.show("fast");
	});
	
	$(document).click(function() {
		divFormUpload.hide("fast");
	});
	
	var init = function() {
		
		
		loadProfile();
		
		var get = location.search; // строка GET запроса
		 if(get != '') {
		  tmp = (get.substr(1)).split('='); // разделяем переменные
//		 		  alert("tt= "+"\"{\"user_name\":"+tmp[1]+"}\"");
		 		  
		 }
		 $.ajax({ //передаємо в сервлет імя користувача яке потрібно витягнути з БД
				url: "check-login",
				contentType: "application/x-www-form-urlencoded",
				dataType: "json",
				data: {"user_name":tmp[1]},
		 success: function(user) {
//отримуємо дані з БД і заповнюємо сторінку даними  
				userName.html(user.name);
				userEmail.html(user.info.email);
				userScore.html(user.stats.score);
				userWinnings.html(user.stats.winnings);
				userLosings.html(user.stats.losings);
				userPhotoImg.attr("src",  "resources/img/user/" + user.info.photo);
				divFormUpload.dispaly = none;
			}
		 });
	};
	
	init();

});

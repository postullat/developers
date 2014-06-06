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
	
	//�������� ����� ��� ������ ����������� �� ����
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
	
	// ��������� ��� �����������, ���� ������ �� ���� (����, ��'�, ����������, � �.�.)
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
		
		var get = location.search; // ������ GET �������
		 if(get != '') {
		  tmp = (get.substr(1)).split('='); // ��������� ����������
//		 		  alert("tt= "+"\"{\"user_name\":"+tmp[1]+"}\"");
		 		  
		 }
		 $.ajax({ //�������� � ������� ��� ����������� ��� ������� ��������� � ��
				url: "check-login",
				contentType: "application/x-www-form-urlencoded",
				dataType: "json",
				data: {"user_name":tmp[1]},
		 success: function(user) {
//�������� ��� � �� � ���������� ������� ������  
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

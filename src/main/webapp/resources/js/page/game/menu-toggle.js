$(function() {
	
	var gameHeader = $("#gameHeader");
	var menuToggler = $("#menuToggler");
	var divMenuToggler = $("#divMenuToggler");
	var isHided = true;
	
	
	gameHeader.animate({top: -gameHeader.height()+divMenuToggler.height()},'slow');
	
	divMenuToggler.hover(function() {
		menuToggler.show();
	}, function() {
		menuToggler.hide();
	});
	
	divMenuToggler.click(function() {
		if (isHided) {
			isHided = false;
			gameHeader.animate({top: 0});
		} else {
			isHided = true;
			gameHeader.animate({top: -gameHeader.height()+divMenuToggler.height()});
		}
	});
	
});
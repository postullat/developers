/* отримування координат на канвасі*/


$(function() {

	var activeObjectMenu = $("#activeObjectMenu");
	var divOnCanvas = $("#divOnCanvas");

	document.getElementById("divOnCanvas").oncontextmenu = function() {
		return false;
	};
	
	document.getElementById("activeObjectMenu").oncontextmenu = function() {
		return false;
	};

	divOnCanvas.mousedown(function(e) {
		sleep(190);
		if (e.which === 1) {
			activeObjectMenu.hide();
			// getOffsetRect(canvas);
			var x1;
			var y1;
			x1 = e.pageX - dx;
			y1 = e.pageY - dy;
			
			$.ajax({
				url : "mouse-manage",
				data : {
					"s_x" : x1,
					"s_y" : y1,
					"message" : "left"
				},
				success : function(data) {
					
				}
			});
		}
		// права кнопка
		if (e.which === 3) {
			var x2;
			var y2;
			x2 = e.pageX - dx;
			y2 = e.pageY - dy;
			$.ajax({
				url : "mouse-manage",
				contentType : "application/x-www-form-urlencoded",
				data : {
					"f_x" : x2,
					"f_y" : y2,
					"message" : "right"
				},
				success : function(menuData) {
					// вивести меню активного об'єкту
					var message = menuData[0];
					var menuSet = menuData[1];
					if ("show_menu" == message) {
						if (menuSet.length > 0) {
							activeObjectMenu.html("");
							for ( var i = 0; i < menuSet.length; ++i) {
								activeObjectMenu.append("<div id=\""
										+ menuSet[i]
										+ "\" class=\"activeObjectMenuItem\">"
										+ menuSet[i] + "</div>");
							}
							$(".activeObjectMenuItem").mousedown(function(e) {
								// ліва кнопка на елемент меню
								if (1 == e.which) {
									$.ajax({
										url : "active-object-menu-action",
										data : {
											"task" : e.target.id
										},
										success : function() {
											activeObjectMenu.hide();
										}
									});
								}
							});
							activeObjectMenu.show();
							activeObjectMenu.css({
								left : x2
							});
							activeObjectMenu.css({
								top : y2
							});
						}
					}
					if ("hide_menu" == message) {
						activeObjectMenu.hide();
					}
				}
			});
		}
		
	});
	
	
});
/* отримування координат розміщення канвасі на екрані */
function getOffsetRect(elem) {

	var box = elem.getBoundingClientRect();

	var body = document.body;
	var docElem = document.documentElement;

	var scrollTop = window.pageYOffset || docElem.scrollTop || body.scrollTop;
	var scrollLeft = window.pageXOffset || docElem.scrollLeft
			|| body.scrollLeft;

	var clientTop = docElem.clientTop || body.clientTop || 0;
	var clientLeft = docElem.clientLeft || body.clientLeft || 0;

	var top = box.top + scrollTop - clientTop;
	var left = box.left + scrollLeft - clientLeft;

	dy = Math.round(top);
	dx = Math.round(left);
}
function sleep(milliseconds) {
	  var start = new Date().getTime();
	  for (var i = 0; i < 1e7; i++) {
	    if ((new Date().getTime() - start) > milliseconds){
	      break;
	    }
	  }
	}


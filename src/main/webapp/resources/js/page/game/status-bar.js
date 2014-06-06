$(function() {

	var divAllUnitsStatusBar = $("#divAllUnitsStatusBar");
	var divUnitImg = $("#divUnitImg");
	var unitInfo = $("#unitInfo");

	var getStatusBar = function() {
		$.ajax({
			url : "get-status-bar",
			success : function(statusBar) {

				var selectedUnitStatusBar = statusBar.selectedUnitStatusBar;
				if (null != selectedUnitStatusBar) {
					divUnitImg.html("<img id=\"unitImg\" src=\""
							+ selectedUnitStatusBar.initTexture + "\">"
							+ "<br>");
					unitInfo.html("<span id=\"unitName\">"
							+ selectedUnitStatusBar.name + "</span><br>");
					unitInfo.append("Current task: "
							+ selectedUnitStatusBar.task + "<br>");
					unitInfo.append("Amount of code written: "
							+ selectedUnitStatusBar.codeLines + "<br>");
					unitInfo.append("Mood: " + selectedUnitStatusBar.mood
							+ "<br>");
					unitInfo.append("Reputation: " + selectedUnitStatusBar.reputation
							+ "<br>");
					unitInfo.append("Health: " + selectedUnitStatusBar.health);
				} else {
					divUnitImg.html("");
					unitInfo.html("");
				}

				var allUnitStatusBars = statusBar.unitStatusBars;
				divAllUnitsStatusBar.html("");
				for ( var i = 0; i < allUnitStatusBars.length; ++i) {
					var unitStatusBar = allUnitStatusBars[i];
					divAllUnitsStatusBar
							.append("<div class=\"divUnitStatusBar\"><center>"
									+ "<img src=\"" + unitStatusBar.initTexture
									+ "\" width=\"25px\"><br>"
									+ "<span style=\"font-size: 7pt;\">"
									+ unitStatusBar.name + "</span></center>"
									+ "M: " + unitStatusBar.mood + "<br>"
									+ "R: " + unitStatusBar.reputation + "<br>"
									+ "H: " + unitStatusBar.health
									+ "<br></div>");
				}
			}
		});
	};

	setInterval(getStatusBar, 60);

});
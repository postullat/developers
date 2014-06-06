$(function() {

	var unitCanvas = document.getElementById("unit_canvas");

	var ctx = unitCanvas.getContext("2d");
	var HEIGHT = 50;
	var WEIGTH = 50;

	var mapUnits = new Array();
	// ���������� �������� ���
	
	var urlSelectedUnit = "resources/img/texture/unit/selected_unit.png";
	var unitSelectionImg = new Image();
	unitSelectionImg.src = urlSelectedUnit;
	var initCanvas = function() {
		unitCanvas.height = 750;
		unitCanvas.width = 1000;
	};

	var draw = function(img, x, y, angle) {

			rotate(angle, img, x, y);


	};

	var drawUnitList = function() {
		// alert("here");
		for ( var i = 0; i < mapUnits.length; i++) {
			draw(mapUnits[i].textures[mapUnits[i].currentTextureId],
					mapUnits[i].x, mapUnits[i].y, mapUnits[i].angle);

		}
	};

	var rotate = function(angle, img, x, y) {
		angle = angle * 1;

		x = x * 1;
		y = y * 1;

		ctx.save();
		ctx.translate(x + WEIGTH / 2, y + HEIGHT / 2);

		ctx.rotate(angle * Math.PI / 180);
		ctx.drawImage(img, -(WEIGTH / 2), -(HEIGHT / 2));
		ctx.restore();
	};

	var getUnits = function() {

		initCanvas();
		$
				.ajax({
					url : "get_unit",
					contentType : "application/x-www-form-urlencoded",
					data : {
						"message" : "give_me_units"
					},
					success : function(teams) {

						for ( var z = 0; z < teams.length; z++) {

							var team = teams[z];
							var units = team.units;
							for ( var i = 0; i < units.length; i++) {
								var unit = {};
								unit = {
									"id" : units[i].id,
									"x" : units[i].x,
									"y" : units[i].y,
									"angle" : units[i].rotationAngle,
									"currentTextureId" : units[i].currentTextureId,
									"textures" : []
								};
								for(var j=0;j<5;j++){
									var tmpImg = new Image();
									tmpImg.src =  units[i].textures[j];
									unit.textures.push(tmpImg);
								}
								
								mapUnits.push(unit);

							}

						}
						drawUnitList();

					},
					error : function(error) {
						alert(error.responseText);
					}
				});

	};


	var getMovingUnits = function() {
		$
				.ajax({
					url : "get_moving_unit",
					contentType : "application/x-www-form-urlencoded",
					data : {
						"message" : "give_me_moving_units"
					},
					success : function(unitsAndSelectedUnit) {
						if (unitsAndSelectedUnit != null) {
							var units = unitsAndSelectedUnit[0];
							var selectedUnit = unitsAndSelectedUnit[1];
							

							
							for ( var i = 0; i < units.length; i++) {
								for ( var j = 0; j < mapUnits.length; j++) {
									
									if (units[i].id == mapUnits[j].id) {
										ctx.clearRect(mapUnits[j].x, mapUnits[j].y, WEIGTH, HEIGHT);


										mapUnits[j].x = units[i].x;
										mapUnits[j].y = units[i].y;
										mapUnits[j].angle = units[i].rotationAngle;
										mapUnits[j].currentTextureId = units[i].currentTextureId;
										
										draw(
												mapUnits[j].textures[mapUnits[j].currentTextureId],
												mapUnits[j].x, mapUnits[j].y,
												mapUnits[j].angle);
										
										
									}
								}
							}
							
								draw(unitSelectionImg,selectedUnit.x,selectedUnit.y,0);
						}

					},
					error : function(error) {

					}
				});
	};

	getUnits();
	setInterval(getMovingUnits, 60);
});

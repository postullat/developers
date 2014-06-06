package com.epam.lab.developers.servlet.json;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.developers.game.map.unit.Unit;

public class UnitJson {

	private int currentTextureId;
	private int id;
	private int x;
	private int y;
	private int rotationAngle;
	
	
	
	
	public UnitJson(int currentTextureId, int id, int x, int y,
			int rotationAngle) {
		super();
		this.currentTextureId = currentTextureId;
		this.id = id;
		this.x = x;
		this.y = y;
		this.rotationAngle = rotationAngle;
	}




	public static List<UnitJson> parseUnitListToJsonUnitList(List<Unit> units){
		
		List<UnitJson> jsonUnits = new ArrayList<>();
		for(Unit unit:units ){
			jsonUnits.add(new UnitJson(unit.getCurrentTextureId(), unit.getId(), unit.getX(), unit.getY(), unit.getRotationAngle()));
		}
		
		return jsonUnits;
		
	}
}

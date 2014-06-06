package com.epam.lab.developers.game.map.unit;

import java.util.List;

public class SeniorDeveloper extends Unit {

	public SeniorDeveloper(int id,int x, int y, int rotationAngle, List<String> textures,String name) {
		super(id,x, y, rotationAngle, textures,name);
		codeLinesPerTimeSpan = 5;
		
	}
	
	@Override
	public void action() {
		switch (task.getTask()){
		
		default : super.action();
			}
	}
	
}

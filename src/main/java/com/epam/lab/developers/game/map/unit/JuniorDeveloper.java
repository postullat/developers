package com.epam.lab.developers.game.map.unit;

import java.util.List;

public class JuniorDeveloper extends Unit {

	
public JuniorDeveloper(int id,int x, int y, int rotationAngle, List<String> textures, String name) {
	super(id, x, y, rotationAngle, textures, name);
	codeLinesPerTimeSpan = 1;
}
@Override
public void action() {
	// TODO Auto-generated method stub
	
	switch (task.getTask()){
	
	default : super.action();
		}
	
}


}

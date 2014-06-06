package com.epam.lab.developers.game.map.unit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.lab.developers.game.map.object.active.CoffeeMashine;

public class MiddleDeveloper extends Unit {

	public static final String COFFEE_BURST="Oh so much coffee";
	public static final long TIME_OF_GIVIN_BONUS = 120000;
	

	public MiddleDeveloper(int id,int x, int y, int rotationAngle,	List<String> textures, String name) {
		super(id, x, y, rotationAngle, textures,name);		
			codeLinesPerTimeSpan = 2;
			
			Set<String> tmp=new HashSet<String>();
			tmp.add("coffeeBurst");
			availableMethods.put(CoffeeMashine.class,tmp);
			
	}
	
	
	public void coffeeBurst() {
		System.out.println("coffeeBurst");		 
		unitStatus.addEffect(COFFEE_BURST, new Effect(TIME_OF_GIVIN_BONUS).addFeature(UnitStatus.CODE_MULTIPLIER, 0.06));
	}
	
	
	@Override
	public void action() {
		
		switch (task.getTask()){
		case "coffeeBurst" :   this.coffeeBurst();
		break;		
		default : super.action();
			}
				
		// TODO Auto-generated method stub
		
	}
	
}

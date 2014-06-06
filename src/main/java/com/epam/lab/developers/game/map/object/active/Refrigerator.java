package com.epam.lab.developers.game.map.object.active;

import com.epam.lab.developers.game.map.unit.Effect;
import com.epam.lab.developers.game.map.unit.MiddleDeveloper;
import com.epam.lab.developers.game.map.unit.SeniorDeveloper;
import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.game.map.unit.UnitStatus;

public class Refrigerator extends ActiveObject {
	
	// effects
	public static final String FOOD = "Food";
	public static final String STEALER = "Stealer";
	
	// methods
	private static final String STEAL_FOOD = "Steal food";
	private static final String TAKE_FOOD = "Take food";

	public Refrigerator(int x, int y, int rotationAngle, String path) {
		super(x, y, rotationAngle, path);
		
		
		availableMethods.get(Unit.class).add(TAKE_FOOD);
		availableMethods.get(SeniorDeveloper.class).add(STEAL_FOOD);
		availableMethods.get(MiddleDeveloper.class).add(STEAL_FOOD);
//		Set<String> tmp = availableMethods.get(SeniorDeveloper.class);
//		tmp.add(STEAL_FOOD);tmp.add(TAKE_FOOD);
//		tmp = availableMethods.get(MiddleDeveloper.class);
//		tmp.add(STEAL_FOOD); tmp.add(TAKE_FOOD);
		
	

	}

	@Override
	public float use(Unit unit) {
		// TODO Auto-generated method stub
		
		if (unitChecking(unit))
		  switch (unit.getTask().getTask()){
			
			case STEAL_FOOD		 :   this.steal();
				break;
			case TAKE_FOOD		 :   this.takeFood();
				break;	
				
			default : return super.use(unit);
		
				}
		return processPercentage+=5;
		
	}

	
	private void takeFood() {
		activeUnit.getUnitStatus().createEffect(FOOD, CookStove.COOK, Effect.INFINITY, 1, 1);
	}

	private void steal() {
		
		activeUnit.getUnitStatus().createEffect(FOOD, CookStove.COOK, Effect.INFINITY, 1, 1);
		activeUnit.getUnitStatus().createEffect(STEALER, UnitStatus.REPUTATION, 5000, -1, -20);
		
		
		
	}
	
	
//	
//	@Override
//	public Set<String> getAvailableMethods(Unit unit) {
//		// TODO Auto-generated method stub
//		Set tmp = new HashSet<>(super.getAvailableMethods(unit));
//		
//		
////		if(unit.getUnitStatus().getEffect(BUFF)!=null)
////			if(unit.getUnitStatus().getEffect(BUFF).getFeature(UnitStatus.CODE_MULTIPLIER) < MAX_CODE_MULTIPLIER_BONUS)		
////				return tmp;			
////			else {				
////				tmp.remove("use");
////				return tmp;
////				
////			}
//		return tmp;
//		
//	}
	
	
	
	
	
	

}

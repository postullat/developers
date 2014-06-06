package com.epam.lab.developers.game.map.object.active;

import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.game.map.unit.UnitStatus;

public class CookStove extends ActiveObject {
	
	
	public static final String COOK = "Cook";
	
	public CookStove(int x, int y, int rotationAngle, String path) {
		super(x, y, rotationAngle, path);
		additionalMethods.get(Unit.class).add(COOK);
	}

	@Override
	public float use(Unit unit) {
		// TODO Auto-generated method stub
		
		if (unitChecking(unit))
			switch (unit.getTask().getTask()){
			
			case COOK		 :   this.cook();
				break;
				
			default : return super.use(unit);
		
				}
		
		return processPercentage+=5;
		
	}
	
	public void cook() {
		
		if (activeUnit.getUnitStatus().getEffect(UnitStatus.HUNGRY) != null) {
			activeUnit.getUnitStatus().getEffects().remove(UnitStatus.HUNGRY);
		}
		if (activeUnit.getUnitStatus().getEffect(Refrigerator.FOOD) != null) {
			activeUnit.getUnitStatus().getEffects().remove(Refrigerator.FOOD);
		}
		activeUnit.getUnitStatus().createEffect("Good food", UnitStatus.HEALTH, 15000, 1, 100);
		
	}

}

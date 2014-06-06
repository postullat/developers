package com.epam.lab.developers.game.map.object.active;

import java.util.HashSet;
import java.util.Set;

import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.game.map.unit.UnitStatus;

public class CoffeeMashine extends ActiveObject {
	
	public static final double STANDART_CODE_MULTIPLIER_BONUS = 0.03;
	public static final double MAX_CODE_MULTIPLIER_BONUS = 0.3;
	public static final long   TIME_OF_GIVIN_BONUS = 15000;
	public static final String BUFF = "Hot Coffee";
	
	
	

	public CoffeeMashine(int x, int y, int rotationAngle, String path) {
		super(x, y, rotationAngle, path);
		// TODO Auto-generated constructor stub
	}



	@Override
	public float use(Unit unit) {
		// TODO Auto-generated method stub
		
		if (unitChecking(unit))
			switch (unit.getTask().getTask()){
			
			case "use"		 :   this.use();
				break;
				
			default : return super.use(unit);
		
				}
		
		return processPercentage += 5;
		
	}
	
	@Override
	public Set<String> getAvailableMethods(Unit unit) {
		// TODO Auto-generated method stub
		Set tmp = new HashSet<>(super.getAvailableMethods(unit));
		
		if(unit.getUnitStatus().getEffect(BUFF)!=null)
			if(unit.getUnitStatus().getEffect(BUFF).getFeature(UnitStatus.CODE_MULTIPLIER) < MAX_CODE_MULTIPLIER_BONUS)		
				return tmp;			
			else {				
				tmp.remove("use");
				return tmp;
				
			}
		return tmp;
		
	}
	
	
	@Override
	public void use() {
		
		// TODO Auto-generated method stub
			
		//Effect buff = activeUnit.getUnitStatus().getEffect(BUFF);
	
		
		activeUnit.getUnitStatus().createEffect(BUFF, UnitStatus.CODE_MULTIPLIER, TIME_OF_GIVIN_BONUS, STANDART_CODE_MULTIPLIER_BONUS, MAX_CODE_MULTIPLIER_BONUS);
		
//		
//		if(buff!=null){
//			if (buff.getBonus(UnitStatus.CODE_MULTIPLIER) < MAX_CODE_MULTIPLIER_BONUS)
//				buff.addBonus(UnitStatus.CODE_MULTIPLIER, buff.getBonus(UnitStatus.CODE_MULTIPLIER)+this.STANDART_CODE_MULTIPLIER_BONUS);
//			
//		} else			
//			activeUnit.getUnitStatus().addEffect(BUFF, (new Effect(TIME_OF_GIVIN_BONUS).addBonus(UnitStatus.CODE_MULTIPLIER, this.STANDART_CODE_MULTIPLIER_BONUS)));

		super.use();
		
		
	}



}

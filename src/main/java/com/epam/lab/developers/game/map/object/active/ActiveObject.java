package com.epam.lab.developers.game.map.object.active;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.epam.lab.developers.game.Active;
import com.epam.lab.developers.game.Usable;
import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.unit.Effect;
import com.epam.lab.developers.game.map.unit.JuniorDeveloper;
import com.epam.lab.developers.game.map.unit.MiddleDeveloper;
import com.epam.lab.developers.game.map.unit.SeniorDeveloper;
import com.epam.lab.developers.game.map.unit.Unit;


public abstract class ActiveObject extends MapObject implements	Active, Usable {
	

	protected ObjectStatus objectStatus =new ObjectStatus();
	protected Unit activeUnit;
	protected float processPercentage=0;
	protected transient Map<String , Effect> effects=new HashMap<String , Effect>(); 
	public static final String BREACKDOWN = "BREACKDOWN";
	
	protected transient Map<Class<? extends Unit>,Set<String>> availableMethods=new HashMap<>();
	protected transient Map<Class<? extends Unit>,Set<String>> additionalMethods=new HashMap<>();
	

	public ActiveObject(int x, int y, int rotationAngle, String path) {
		super(x, y, rotationAngle, path);
		Set<String> tmp= new HashSet<String>();
		tmp.add("use"); tmp.add("breakDown");		
		availableMethods.put(Unit.class,tmp);		
		availableMethods.put(JuniorDeveloper.class,new HashSet<String>());
		availableMethods.put(MiddleDeveloper.class,new HashSet<String>());
		availableMethods.put(SeniorDeveloper.class,new HashSet<String>());		
		tmp= new HashSet<String>();
		tmp.add("repair");
		additionalMethods.put(Unit.class,tmp);		
		additionalMethods.put(JuniorDeveloper.class,new HashSet<String>());
		additionalMethods.put(MiddleDeveloper.class,new HashSet<String>());
		additionalMethods.put(SeniorDeveloper.class,new HashSet<String>());	
		
	}

	public void use(){
			
	}
	

	
	
	
	@Override
	public float use(Unit unit) {
		
		executeEffects();
		
		

		if(unitChecking(unit))
			switch (unit.getTask().getTask()){
			
			case "use"		 :   this.use();
				break;
				
			case "repair"		 :   this.repair();
				break;
				
			case "breakDown"		 :   this.breakDown();
				break;
			
			default : System.err.println("unknown method");
	
				}
		
		return processPercentage+=5;
		
	
	}
	
	
	
	
	
	
	
	private void executeEffects() {
			for (String tmp : objectStatus.getEffects().keySet()) {
				Effect effect = objectStatus.getEffects().get(tmp);
				checkTaskFeature(effect.getFeature(activeUnit.getTask().getTask()));
			}
		
	}
	
	

	private void checkTaskFeature(Double feature) {
		if (feature != null) {
			if (feature < 0) {
				activeUnit.getTask().setTask("stop");
			}
		}
	}

	
	
	
	
	public Set<String> getAvailableMethods(Unit unit) {
		
		Set<String> tmp= new HashSet<String>(availableMethods.get(Unit.class));
		Set<String>	addTmp = new HashSet<>(additionalMethods.get(Unit.class));
		
		if (availableMethods.containsKey(unit.getClass()))
			tmp.addAll(availableMethods.get(unit.getClass()));
		
		if (additionalMethods.containsKey(unit.getClass()))
			addTmp.addAll(additionalMethods.get(unit.getClass()));
		
		
negative:for (String availableMethod : tmp) {
			for (String tmpString : unit.getUnitStatus().getEffects().keySet()) { 
				Effect effect = unit.getUnitStatus().getEffects().get(tmpString);
				if (effect.getFeatures().keySet().contains(availableMethod)) {
					if (effect.getFeature(availableMethod) < 0) {
						tmp.remove(availableMethod);
						continue negative;
					}
				}
			}
			
			for (String tmpString : objectStatus.getEffects().keySet()) { 
				Effect effect = objectStatus.getEffects().get(tmpString);
				if (effect.getFeatures().keySet().contains(availableMethod)) {
					if (effect.getFeature(availableMethod) < 0) {
						tmp.remove(availableMethod);
						break;
					}
				}
			}
						
		}
		
		
		for (String additionalMethod : addTmp) {
			boolean isPositiveFeature=false;
			for (String tmpString : unit.getUnitStatus().getEffects().keySet()) { 
				Effect effect = unit.getUnitStatus().getEffects().get(tmpString);				
				if (effect.getFeatures().keySet().contains(additionalMethod)) {
					if (effect.getFeature(additionalMethod) <0) {
						isPositiveFeature=false;						
						break;						
					}
					else isPositiveFeature=true;
				}
			}
			
			for (String tmpString : objectStatus.getEffects().keySet()) { 
				Effect effect = objectStatus.getEffects().get(tmpString);				
				if (effect.getFeatures().keySet().contains(additionalMethod)) {
					if (effect.getFeature(additionalMethod) <0) {
						isPositiveFeature=false;						
						break;						
					}
					else isPositiveFeature=true;
				}
			}
			
			if(isPositiveFeature) tmp.add(additionalMethod);
		}
		
		return tmp;			
		
	}
	
	
	

	protected boolean unitChecking(Unit unit){
		if (this.activeUnit != unit) {
			processPercentage=0;
			this.activeUnit=unit;
		}		
		return unit!=null;
	}
	
	

	public float getProcessPercentage() {
		return processPercentage;
	}

	public void setProcessPercentage(float percentage) {
		this.processPercentage = percentage>100&&percentage<0 ? 0 : percentage;
	}
	
	
	@Override
	public void action() {

		
	}

	private void breakDown() {
		// TODO Auto-generated method stub
		
		
//		Effect buff = activeUnit.getUnitStatus().getEffect(BUFF);
		
		if(effects.get(BREACKDOWN)!=null){
			if (effects.get(BREACKDOWN).getFeature("repair") < 100)
				effects.put(BREACKDOWN, new Effect(99999999).addFeature("repair", effects.get(BREACKDOWN).getFeature("repair")+5) );
		} else			
			effects.put(BREACKDOWN, (new Effect(99999999).addFeature("repair", 5.)));

		System.out.println(effects.get(BREACKDOWN).getFeature("repair"));
//		super.use();
		
//		this.effects.put("Broken", new Effect(99999999).addBonus("breakDown", new Double(100)));
		
	}


	private void repair() {
		// TODO Auto-generated method stub
		
	}

}

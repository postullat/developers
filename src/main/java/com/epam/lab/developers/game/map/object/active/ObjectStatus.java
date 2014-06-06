package com.epam.lab.developers.game.map.object.active;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.epam.lab.developers.game.map.unit.Effect;

public class ObjectStatus {

	private float useMultiplayer;
	
	private float status;
	
	private Map<String, Effect> effects = new HashMap<>();

	
	
	
	
	public float getUseMultiplayer() {
		return useMultiplayer;
	}
	public void setUseMultiplayer(float useMultiplayer) {
		this.useMultiplayer = useMultiplayer;
	}
	public float getStatus() {
		return status;
	}
	public void setStatus(float status) {
		if(status>100) this.status=status;
		else
		this.status = status>0?status:0;
	}
	
	
	public Effect getEffect(String name) {

		return effects.get(name);

	}
	
	public Map<String, Effect> getEffects() {
		for (String name : this.effects.keySet())
			checkTimeNoEnd(name);
		return effects;
	}
	
	private boolean checkTimeNoEnd(String name) {
		Date time = new Date();
		if (effects.containsKey(name)) {
			if (effects.get(name).getStart().getTime()
					+ effects.get(name).getTimeToEnd() - time.getTime() <= 0) {
				effects.remove(name);
				return false;
			}
			return true;
		} else
			return false;
	}

	
	public void createEffect(String name, String nameOfFeature, long timeOfFeature,
			double start, double max) {

		if (effects.get(name) != null) {
			if (max > 0 ? effects.get(name).getFeature(nameOfFeature) < max
					: effects.get(name).getFeature(nameOfFeature) > max) {
				effects.get(name).addFeature(nameOfFeature,
						effects.get(name).getFeature(nameOfFeature) + start);
			}
		} else {
			effects.put(name, new Effect(timeOfFeature).addFeature(nameOfFeature, start));
		}

	}

	
	
	
	
}

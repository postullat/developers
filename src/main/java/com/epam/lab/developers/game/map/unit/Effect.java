package com.epam.lab.developers.game.map.unit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Effect {

	public static final long INFINITY = 999999999L;
	private Date start;
	private long timeToEnd;
	boolean visibility;
	
	private Map<String, Double>features = new HashMap<String, Double>();
	
	
	public Effect(long timeToEnd) {
		super();
		this.start = new Date();
		this.visibility = true;
		this.timeToEnd = timeToEnd; 
	}

	public Effect(long timeToEnd, boolean visibility ) {
		super();
		this.start = new Date();
		this.visibility = visibility;
		this.timeToEnd = timeToEnd; 
	}

	
	
	
	public Date getStart() {
		return start;
	}


	public long getTimeToEnd() {
		return timeToEnd;
	}


	public void setTimeToEnd(long timeToEnd) {
		this.timeToEnd = timeToEnd;
	}



	public Map<String, Double> getFeatures() {
		return features;
	}

	
	public Double getFeature(String name){
		
		if (features.containsKey(name)) return features.get(name); 
				else return null;
		
	}
	
	
	

	public boolean isVisible() {
		return visibility;
	}


	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	

	public Effect addFeature(String name , Double Percentagebonus) {
		
		
		
		this.features.put(name , Percentagebonus);
		
		return this;
	}
	
	
	
}

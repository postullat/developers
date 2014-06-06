package com.epam.lab.developers.game.map.unit;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class UnitStatus {

	private double codeMultiplier;// множник для швидкості написання коду,
									// напряму залежить від настрою ситості і
									// життя
	private double mood; // настрій юніта, якщо ситість і здоровя нормальні
	private double reputation;
	private double health; // здоров'я, змінюється коли бються

	public static final String HUNGRY = "Hungry";

	public static final String CODE_MULTIPLIER = "CODE_MULTIPLIER";
	public static final String MOOD = "MOOD";
	public static final String REPUTATION = "REPUTATION";
	public static final String HEALTH = "HEALTH";

	private Map<String, Effect> effects = Collections.synchronizedMap(new HashMap<String, Effect>());

	public UnitStatus() {
		this.reputation = 0;
		this.health = 100;
		this.mood = 100;
		this.codeMultiplier = 1;

	}

	public void randomEffects() {
		// ймовірність зголодніти - 5%
		int probability = 5;
		int rand = new Random().nextInt(100 / probability);
		if (0 == rand) {
			String effectName = HUNGRY;
			createEffect(effectName, HEALTH, Effect.INFINITY, -1, -100);
		}
	}

	public UnitStatus(double codeMultiplie, double mood, double bellyful,
			double healt) {
		this.reputation = bellyful;
		this.health = healt;
		this.codeMultiplier = codeMultiplie;
		this.mood = mood;
	}

	public void addEffect(String name, Effect effect) {

		effects.put(name, effect);

	}

	public Map<String, Effect> getEffects() {
		Iterator<String> iterator = effects.keySet().iterator();
		Date time = new Date();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (effects.get(name).getStart().getTime()
					+ effects.get(name).getTimeToEnd() - time.getTime() <= 0) {
				iterator.remove();
			}
		}
		return effects;
	}

	public Effect getEffect(String name) {

		return effects.get(name);

	}

	public double getCodeMultiplier() {
		double tmpCodeMultiplier = 1;
		for (String name : this.effects.keySet()) {
			Effect effect = effects.get(name);
			if (null != effect) {
				Double bonus = effect.getFeature(CODE_MULTIPLIER);
				if (null != bonus) {
					tmpCodeMultiplier += bonus;
					System.out.println("++++++++++++++++" + bonus);
				}
			}
		}
		return codeMultiplier * tmpCodeMultiplier;
	}

	public void createEffect(String name, String nameOfFeature, long timeOfFeature,
			double start, double max, boolean visible) {

		if (effects.get(name) != null) {
			if (max > 0 ? effects.get(name).getFeature(nameOfFeature) < max
					: effects.get(name).getFeature(nameOfFeature) > max)
				effects.get(name).addFeature(nameOfFeature,
						effects.get(name).getFeature(nameOfFeature) + start);

		} else
			addEffect(name,
					new Effect(timeOfFeature, visible).addFeature(nameOfFeature, start));
	}

	public Effect createEffect(String name, String nameOfFeature, long timeOfFeature,
			double start, double max) {

		if (effects.get(name) != null) {
			if (max > 0 ? effects.get(name).getFeature(nameOfFeature) < max
					: effects.get(name).getFeature(nameOfFeature) > max) {
				effects.get(name).addFeature(nameOfFeature,
						effects.get(name).getFeature(nameOfFeature) + start);
			}
			return effects.get(name);
		} else {
			Effect effect = new Effect(timeOfFeature).addFeature(nameOfFeature, start);
			addEffect(name, effect);
			return effect;
		}

	}

	public void setCodeMultiplier(double codeMultiplier) {
		this.codeMultiplier = codeMultiplier;
	}

	public double getMood() {
		return mood;
	}

	public void setMood(double mood) {
		this.mood = mood;
	}

	public double getReputation() {
		return reputation;
	}

	public void setReputation(double reputation) {
		this.reputation = reputation;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

}

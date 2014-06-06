package com.epam.lab.developers.game.map.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.epam.lab.developers.game.Active;
import com.epam.lab.developers.game.map.algorithm_way.Step;
import com.epam.lab.developers.game.map.object.active.ActiveObject;
import com.epam.lab.developers.game.map.object.active.CoffeeMashine;
import com.epam.lab.developers.game.map.object.active.Notebook;
import com.epam.lab.developers.game.map.object.active.Printer;
import com.epam.lab.developers.game.map.object.active.Server;

public abstract class Unit implements Active {

	protected int codeLinesPerTimeSpan = 100;
	public static final String STOP = "stop";
	
	protected int id;
	protected int x;
	protected int y;
	protected int rotationAngle;
	protected List<String> textures = new ArrayList<>();
	protected int currentTextureId = 0;
	protected String name;
	protected transient Map<Class<? extends ActiveObject>, Set<String>> availableMethods = new HashMap<Class<? extends ActiveObject>, Set<String>>();
	protected UnitTask task = new UnitTask("none");
	protected UnitStatus unitStatus;
	protected float codeLines;

	public Unit() {

	}

	public Unit(int id, int x, int y, int rotationAngle, List<String> textures,
			String name) {

		this.id = id;

		// // TODO ��������
		this.x = x * 50;
		this.y = y * 50;
		// //
		this.rotationAngle = rotationAngle;
		this.textures = textures;
		this.name = name;

		this.unitStatus = new UnitStatus();

		HashSet<String> tmp = new HashSet<String>();
	//	tmp.add("repair");
		tmp.add("breakDown");
		availableMethods.put(CoffeeMashine.class, tmp);

		tmp = new HashSet<String>();
		tmp.add("use");
//		tmp.add("repair");
		tmp.add("breakDown");
		availableMethods.put(Printer.class, tmp);

		tmp = new HashSet<String>();
		tmp.add("use");
	//	tmp.add("repair");
		tmp.add("breakDown");
		availableMethods.put(Server.class, tmp);

		tmp = new HashSet<String>();
		tmp.add("code");
		availableMethods.put(Notebook.class, tmp);
	}

	@Override
	public void action() {
		this.unitStatus.randomEffects();
		executeEffects();
		if (task.getWay().size() > 0)
			this.move(task.getWay());
		else
			switch (task.getTask()) {
			case "move":
				task.setTask(STOP);
				break;
			case "code":
				this.code();
				break;
			case "use":
				this.use();
				System.out.println(this.unitStatus.getCodeMultiplier());
				break;
			case "repair":
				this.repair();
				break;
			case "breakDown":
				this.breakDown();
				break;
			case STOP: 
				break;
			default : if(null!= this.task.getTarget() && this.task.getTarget() instanceof ActiveObject) {
				if (((ActiveObject) this.task.getTarget()).use(this) > 100) {
					this.getTask().setTask(STOP);
				}
			}
					   
			}

	}

	private void executeEffects() {
		Map<String, Effect> effects = unitStatus.getEffects();
		for (String tmp : effects.keySet()) {
			Effect effect = effects.get(tmp);
			checkHeathFeature(effect.getFeature(UnitStatus.HEALTH));
			checkMoodFeature(effect.getFeature(UnitStatus.MOOD));
			checkReputationFeature(effect.getFeature(UnitStatus.REPUTATION));
			checkTaskFeature(effect.getFeature(task.getTask()));
		}
	}

	private void checkTaskFeature(Double feature) {
		if (feature != null) {
			if (feature < 0) {
				task.setTask(STOP);
			}
		}
	}

	private void checkReputationFeature(Double feature) {
		if (feature != null) {
			unitStatus.setReputation(unitStatus.getReputation() + feature / 100);
		}
	}

	private void checkMoodFeature(Double feature) {
		if (feature != null) {
			unitStatus.setMood(unitStatus.getMood() + feature / 100);
		}
	}

	private void checkHeathFeature(Double feature) {
		if (feature != null) {
			if (unitStatus.getHealth() > 1 && unitStatus.getHealth() <= 100) {
				unitStatus.setHealth(unitStatus.getHealth() + feature / 500);
			}
			if (unitStatus.getMood() > 1 && unitStatus.getMood() <= 100) {
				unitStatus.setMood(unitStatus.getMood() + feature / 100);
			}
			if (unitStatus.getMood() > 100) {
				unitStatus.setMood(100);
			}
			if (unitStatus.getHealth() > 100) {
				unitStatus.setHealth(100);
			}
		}
	}

	public UnitTask getTask() {
		return task;
	}

	public void setTask(UnitTask task) {
		this.task = task;
	}

	private void breakDown() {
		((ActiveObject) this.task.getTarget()).use(this);

	}

	private void repair() {

	}

	private void use() {
		if (((ActiveObject) this.task.getTarget()).use(this) >= 100)
			this.task.setTask(STOP);
	}

	public float getCodeLines() {
		return codeLines;
	}

	protected void code() {
		codeLines += this.codeLinesPerTimeSpan
				* unitStatus.getCodeMultiplier();
	}

	public Set<String> getAvailableMethods(ActiveObject activeObject) {
		if (availableMethods.containsKey(activeObject.getClass()))
			return availableMethods.get(activeObject.getClass());
		else
			return new HashSet<String>();
	}

	public void move(LinkedList<Step> way) {
		Step step = way.pollLast();

		if (null != step) {

			if (way.size() == 0)
				currentTextureId = 0;
			else if (currentTextureId < textures.size() - 1)
				currentTextureId++;
			else
				currentTextureId = 1;

			if (this.x < step.getX())
				this.rotationAngle = 270;
			else if (this.x > step.getX())
				this.rotationAngle = 90;
			else if (this.y < step.getY())
				this.rotationAngle = 0;
			else if (this.y > step.getY())
				this.rotationAngle = 180;

			this.x = step.getX();
			this.y = step.getY();

		}

	}

	public UnitStatus getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(UnitStatus unitStatus) {
		this.unitStatus = unitStatus;
	}

	public List<String> getTextures() {
		return textures;
	}

	public int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}

	public int getRotationAngle() {
		return rotationAngle;
	}

	protected void setRotationAngle(int rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getCurrentTextureId() {
		return currentTextureId;
	}

}

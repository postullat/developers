package com.epam.lab.developers.game.map.unit;

import java.util.LinkedList;

import com.epam.lab.developers.game.map.algorithm_way.Step;
import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.object.active.ActiveObject;

public class UnitTask {

	private transient MapObject target;
	private String task;

	private LinkedList<Step> way = new LinkedList<>();

	public UnitTask(String task) {
		super();
		this.task = task;
	}

	public UnitTask(MapObject target, String task, LinkedList<Step> way) {
		super();
		this.target = target;
		this.task = task;
		this.way = way;
	}

	public MapObject getTarget() {
		return target;
	}

	public void setTarget(MapObject target) {
		this.target = target;
	}

	public LinkedList<Step> getWay() {
		return way;
	}

	public void setWay(LinkedList<Step> way) {
		this.way = way;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		if (target instanceof ActiveObject) ((ActiveObject)target).setProcessPercentage(0);
		this.task = task;

	}

}

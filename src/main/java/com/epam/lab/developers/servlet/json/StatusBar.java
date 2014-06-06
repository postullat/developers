package com.epam.lab.developers.servlet.json;

import java.util.ArrayList;
import java.util.List;

public class StatusBar {

	private float mycodeLines;
	private List<UnitStatusBar> unitStatusBars = new ArrayList<>();
	private UnitStatusBar selectedUnitStatusBar;

	public StatusBar() {

	}
	
	public StatusBar(float mycodeLines, List<UnitStatusBar> unitStatusBars,
			UnitStatusBar selectedUnitStatusBar) {
		super();
		this.mycodeLines = mycodeLines;
		this.unitStatusBars = unitStatusBars;
		this.selectedUnitStatusBar = selectedUnitStatusBar;
	}
	
	public static class OpponentCodeLine {
		
		private String name;
		private float codeLines;
		
		public OpponentCodeLine(String name, float codeLines) {
			super();
			this.name = name;
			this.codeLines = codeLines;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public float getCodeLines() {
			return codeLines;
		}
		
		public void setCodeLines(float codeLines) {
			this.codeLines = codeLines;
		}
		
	}

	public static class UnitStatusBar {

		private String name;
		private String initTexture;
		private double mood;
		private double reputation;
		private double health;
		private String task;
		private float codeLines;
		
		public UnitStatusBar(String name, String initTexture, double mood,
				double reputation, double health, String task, float codeLines) {
			super();
			this.name = name;
			this.initTexture = initTexture;
			this.mood = mood;
			this.reputation = reputation;
			this.health = health;
			this.task = task;
			this.codeLines = codeLines;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInitTexture() {
			return initTexture;
		}

		public void setInitTexture(String initTexture) {
			this.initTexture = initTexture;
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

		public String getTask() {
			return task;
		}

		public void setTask(String task) {
			this.task = task;
		}

		public float getCodeLines() {
			return codeLines;
		}

		public void setCodeLines(float codeLines) {
			this.codeLines = codeLines;
		}

	}

	public float getMycodeLines() {
		return mycodeLines;
	}

	public void setMycodeLines(float mycodeLines) {
		this.mycodeLines = mycodeLines;
	}
	
	public List<UnitStatusBar> getUnitStatusBars() {
		return unitStatusBars;
	}

	public void setUnitStatusBars(List<UnitStatusBar> unitStatusBars) {
		this.unitStatusBars = unitStatusBars;
	}

	public UnitStatusBar getSelectedUnitStatusBar() {
		return selectedUnitStatusBar;
	}

	public void setSelectedUnitStatusBar(UnitStatusBar selectedUnitStatusBar) {
		this.selectedUnitStatusBar = selectedUnitStatusBar;
	}

}

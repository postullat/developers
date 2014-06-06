package com.epam.lab.developers.servlet.json;

import com.epam.lab.developers.game.Game;

public class GameJson {

	private String name;
	private UserJson creator;
	private String  dateOfCreation;
	private boolean isJoinAvailable;
	
	public GameJson() {
		// TODO Auto-generated constructor stub
	}
	
	public GameJson(Game game, boolean isJoinAvailable) {
		this.name = game.getName();
		this.creator = new UserJson(game.getCreator());
		this.dateOfCreation = game.getDateOfCreation().toString();
		this.isJoinAvailable = isJoinAvailable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserJson getCreator() {
		return creator;
	}

	public void setCreator(UserJson creator) {
		this.creator = creator;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public boolean isJoinAvailable() {
		return isJoinAvailable;
	}

	public void setJoinAvailable(boolean isJoinAvailable) {
		this.isJoinAvailable = isJoinAvailable;
	}
	
	
}

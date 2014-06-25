package com.epam.lab.developers.servlet.json;

import com.epam.lab.developers.domain.User;

public class UserJson {

	private String name;
	private User.Info info;
	private User.Statistic stats;
	private float codeLines;
	
	public UserJson(User user) {
		this.name = user.getName();
		this.info = user.getInfo();
		this.stats = user.getStats();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User.Info getInfo() {
		return info;
	}

	public void setInfo(User.Info info) {
		this.info = info;
	}

	public User.Statistic getStats() {
		return stats;
	}

	public void setStats(User.Statistic stats) {
		this.stats = stats;
	}

	public float getCodeLines() {
		return codeLines;
	}

	public void setCodeLines(float codeLines) {
		this.codeLines = codeLines;
	}
	
}

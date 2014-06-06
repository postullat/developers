package com.epam.lab.developers.entity;

import com.epam.lab.developers.game.Team;

public class User {
	
	private int id;
	private String name;
	private transient String password;
	private Info info = null;
	private Stats stats = null;
	private Team team;
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, String password, String email, String photo) {
		super();
		this.name = name;
		this.password = password;
		this.info = new Info(email, photo);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public class Info {

		private String email;
		private String photo; // path to user photo
				
		public Info(String email, String photo) {
			super();
			this.email = email;
			this.photo = photo;
		}

		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getPhoto() {
			return photo;
		}
		
		public void setPhoto(String photo) {
			this.photo = photo;
		}
			
	}
	
	public class Stats {

		private int score;
		private int winnings;
		private int losings;
		
		public Stats(int score, int winnings, int losings) {
			super();
			this.score = score;
			this.winnings = winnings;
			this.losings = losings;
		}
		
		public int getScore() {
			return score;
		}
		
		public void setScore(int score) {
			this.score = score;
		}
		
		public int getWinnings() {
			return winnings;
		}
		
		public void setWinnings(int winnings) {
			this.winnings = winnings;
		}
		
		public int getLosings() {
			return losings;
		}
		
		public void setLosings(int losings) {
			this.losings = losings;
		}
		
	}
	

}

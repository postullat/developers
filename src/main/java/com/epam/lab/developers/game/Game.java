package com.epam.lab.developers.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.map.GameMap;
import com.epam.lab.developers.game.map.algorithm_way.MapWrapper;

public class Game {

	public static final float CODE_LINES = 1000; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ, пїЅпїЅ
												// пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
												// пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅФІпїЅ
	// пїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 20 пїЅпїЅ
	private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
	private static final int TIME_PERIOD = 60;

	private boolean isRunningGame = false;
	private Date dateOfCreation;
	// пїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	private String name;
	private GameMap map;
	private int[][] mapBinary;
	private User creator;
	private List<Team> teams = new ArrayList<>();
	private List<User> players = new ArrayList<>();

	// пїЅ GameTask пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ "пїЅпїЅпїЅ-пїЅ-пїЅпїЅпїЅпїЅ", пїЅпїЅ пїЅ пїЅпїЅпїЅпїЅ Game
	// пїЅпїЅпїЅпїЅ Game пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
	private GameTask gameTask;
	private Timer timer = new Timer();
	
	private User userWin = new User();	//юзер який виграв в грі
	private User userLoose = new User();	//юзер що програв
	
	private List<GameChat> chat = new LinkedList<GameChat>();
	
	
//==============================================================================	
	public Game(String name, GameMap map, List<Team> teams, User creator) {
		this.name = name;
		this.map = map;
		this.mapBinary = new MapWrapper(this.map).getMapForAlgorithm();
		this.teams = teams;
		this.creator = creator;
		this.dateOfCreation = new Date();
		addPlayerAndSetTeam(creator);		
	}

	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	public boolean addPlayerAndSetTeam(User user) {
		if (players.size() < map.getPlayerCount()) {
			players.add(user);
			for (Team team : this.teams) {
				boolean isFree = true;
				for (User findedUser : this.players) {
					if (team == findedUser.getTeam()) {
						isFree = false;
						break;
					}
				}
				if (isFree) {
					user.setTeam(team);
					break;
				}
			}
			// пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ - пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ
			if (map.getPlayerCount() == this.players.size()) {
				start();
			}
			return true;
		} else {
			return false;
		}

	}

	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ
	public void start() {
		isRunningGame = true;
		gameTask = new GameTask(this);
		timer.schedule(gameTask, 0, TIME_UNIT.toMillis(TIME_PERIOD));
	}

	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ
	public void finish(User user) {
		isRunningGame = false;
		timer.cancel();	
		
// присвоюємо в класі Гра хто з користувачів вигра а хто програв		
		for(User u: players){
			
			if(u.equals(user)){
				setUserWin(u);
			} else{
				setUserLoose(u);
			}
		}
		System.out.println(getUserWin().getName()+" - Win!!! || "+getUserLoose().getName()+" Looser");
		
		//записати в базу +1 win
		//записати в базу +1 loose
	}
	
	public void stop(){
		
		isRunningGame = false;
		timer.cancel();
	}

	public String getName() {
		return name;
	}

	public int[][] getMapBinary() {
		return mapBinary;
	}
	
	public GameMap getMap() {
		return map;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public User getCreator() {
		return creator;
	}

	public List<User> getPlayers() {
		return players;
	}

	public Timer getTimer() {
		return timer;
	}

	public GameTask getGameTask() {
		return gameTask;
	}

	public boolean isRunningGame() {
		return isRunningGame;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public List<GameChat> getChat() {
		return chat;
	}

	public void setChat(List<GameChat> chat) {
		this.chat = chat;
	}

	public User getUserWin() {
		return userWin;
	}

	public void setUserWin(User userWin) {
		this.userWin = userWin;
	}

	public User getUserLoose() {
		return userLoose;
	}

	public void setUserLoose(User userLoose) {
		this.userLoose = userLoose;
	}
	
	

}

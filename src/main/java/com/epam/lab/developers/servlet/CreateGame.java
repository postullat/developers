package com.epam.lab.developers.servlet;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.MapManager;
import com.epam.lab.developers.entity.MyUserDetails;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.GameMap;

@Controller
@RequestMapping("/create-game")
public class CreateGame {

	static final Logger logger = Logger.getLogger(CreateGame.class);
	private static final String CREATE_NEW_GAME_COMMAND = "create_new_game";
	private static final String JOIN_TO_GAME_COMMAND = "join_to_game";


	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
	
	public @ResponseBody ResponseEntity<String> executeCommand(@RequestParam String command, @RequestParam String gameName, Principal principal, HttpServletRequest request, HttpServletResponse response) {

		User user = ((MyUserDetails) ((Authentication) principal).getPrincipal()).getUser();


		if (command.equals(CREATE_NEW_GAME_COMMAND) && !DataHolder.getInstance().isUserPlaying(user)) {
			createGame(user);
			logger.debug(user.getName() + " has created the game");
			return new ResponseEntity<String>(HttpStatus.OK);
		}

		if (command.equals(JOIN_TO_GAME_COMMAND) && !DataHolder.getInstance().isUserPlaying(user)) { 

			if (gameName != null && DataHolder.getInstance().joinToGame(gameName, user)) {
				
				logger.debug(user.getName() + " join to game:" + gameName);
				return new ResponseEntity<String>(HttpStatus.OK);
				
			} else {

				logger.debug("Game="+gameName+":Amount of users has reached limit in this game");
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	private void createGame(User user) {
		int gameNumber = DataHolder.getInstance().getAmountOfGames() + 1;
		String name = "game " + gameNumber;
		try {
			MapManager mapManager = new MapManager("test");
			GameMap map = mapManager.getMap();
			List<Team> teams = mapManager.getTeams();
			DataHolder.getInstance().createGame(name, map, teams, user);
		} catch (SQLException e) {
			logger.error("Can not retrive map");
			e.printStackTrace();
		}
	}

}

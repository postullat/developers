package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.data.MapManager;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.GameMap;

/**
 * Servlet implementation class GreateGame
 */
@WebServlet("/create-game")
public class CreateGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(CreateGame.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGame() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		if (null != message) {
			User user = LoginData.userLogined(request);
			if (message.equals("create_new_game")) { // запит на створення гри
				if (!DataHolder.getInstance().isUserPlaying(user)) {
					createGame(user);
					logger.debug(user.getName() + " has created the game");
				}
			}
			if (message.equals("join_to_game")) { // запит про приєднання до гри
				if (!DataHolder.getInstance().isUserPlaying(user)) {
					// отимуєм id гри до якої користувач хоче приєднатися
					String gameName = request.getParameter("gameName");
					if (null != gameName) {
						if (DataHolder.getInstance().joinToGame(gameName, user)) {
							logger.debug(user.getName() + " join to game:"
									+ gameName);
						} else {
							response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.getWriter()
									.println(
											"Amount of users has reached limit in this game");
						}
					}
				}
			}
		}
	}

	private void createGame(User user) {
		int gameNumber = DataHolder.getInstance().getAmountOfGames() + 1;
		String name = "game " + gameNumber;
		GameMap map = null;
		try {
			MapManager mapManager = new MapManager("test");
			map = mapManager.getMap();
			List<Team> teams = mapManager.getTeams();
			DataHolder.getInstance().createGame(name, map, teams, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

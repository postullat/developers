package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.servlet.json.GameJson;
import com.epam.lab.developers.servlet.json.UserJson;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetGameData
 */
@WebServlet("/get-game-data")
public class GetGameData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGameData() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		if(null != message){
			User user = LoginData.userLogined(request);
			if (null != user) {
				if (message.equals("get_game_info")) {
					String gameName = request.getParameter("gameName");
					Game gameByName = DataHolder.getInstance().getGameByName(gameName);
					if (null != gameByName) {
						GameJson gameJson = DataHolder.getInstance().getGameJson(gameByName);
						String json = new Gson().toJson(gameJson);
						response.getWriter().println(json);
					}
				}
				Game game = DataHolder.getInstance().getGame(user);
				if (null != game) {
					if (message.equals("get_players")) { // отримати список гравців
						Set<User> opponents = new HashSet<>(game.getPlayers());
						opponents.remove(user);
						List<UserJson> opponentsJson = new ArrayList<>();
						for (User opponent : opponents) {
							UserJson opponentJson = new UserJson(opponent);
							opponentJson.setCodeLines(opponent.getTeam().getCodeLines());
							opponentsJson.add(opponentJson);
						}
						UserJson userJson = new UserJson(user);
						userJson.setCodeLines(user.getTeam().getCodeLines());
						Object[] youOpponentsMaxCL = new Object[] {
							userJson,
							opponentsJson,
							Game.CODE_LINES
						};
						String json = new Gson().toJson(youOpponentsMaxCL);
						response.getWriter().println(json);
					}
					if (message.equals("get_game_name")) { // отримати назву гри
						String json = new Gson().toJson(game.getName());
						response.getWriter().println(json);
					}
				}
			}
		}
	}

}

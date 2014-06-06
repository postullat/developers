package com.epam.lab.developers.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.google.gson.Gson;

/**
 * Servlet implementation class TimerWaitingForGame
 */
@WebServlet("/timer-waiting")
public class TimerWaitingForGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TimerWaitingForGame() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		boolean p = false;
		User user = LoginData.userLogined(request);
		if (null != user) {
			Game game = DataHolder.getInstance().getGame(user);
			if (null != game) {
				p = game.isRunningGame();
				String json = new Gson().toJson(p);
				response.getWriter().println(json);
			}
		}
	}

}

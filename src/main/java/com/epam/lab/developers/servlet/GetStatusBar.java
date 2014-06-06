package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.game.map.unit.UnitStatus;
import com.epam.lab.developers.game.map.unit.UnitTask;
import com.epam.lab.developers.servlet.json.StatusBar;
import com.epam.lab.developers.servlet.json.StatusBar.UnitStatusBar;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetStatusBar
 */
@WebServlet("/get-status-bar")
public class GetStatusBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStatusBar() {
        super();
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
		User user = LoginData.userLogined(request);
		if (null != user) {
			Game game = DataHolder.getInstance().getGame(user);
			if (null != game) {
				Team team = user.getTeam();
				float mycodeLines = team.getCodeLines();
				List<UnitStatusBar> unitStatusBars = new ArrayList<>();
				for (Unit unit : team.getUnits()) {
					String name = unit.getName();
					String initTexture = unit.getTextures().get(0);
					double mood = 0;
					double reputation = 0;
					double health = 0;
					float codeLines = unit.getCodeLines();
					UnitStatus unitStatus = unit.getUnitStatus();
					if (null != unitStatus) {
						mood = Math.round(unit.getUnitStatus().getMood());
						reputation = Math.round(unit.getUnitStatus().getReputation());
						health = Math.round(unit.getUnitStatus().getHealth());
					}
					String task = "";
					UnitTask unitTask = unit.getTask();
					if (null != unitTask) {
						task = unitTask.getTask();
					}
					unitStatusBars.add(new UnitStatusBar(name, initTexture, mood, reputation, health, task, codeLines));
				}
				Unit activeUnit = team.getActiveUnit();
				UnitStatusBar selectedUnitStatusBar = null;
				if (null != activeUnit) {
					String name = activeUnit.getName();
					String initTexture = activeUnit.getTextures().get(0);
					double mood = 0;
					double reputation = 0;
					double health = 0;
					float codeLines = activeUnit.getCodeLines();
					UnitStatus unitStatus = activeUnit.getUnitStatus();
					if (null != unitStatus) {
						mood = Math.round(activeUnit.getUnitStatus().getMood());
						reputation = Math.round(activeUnit.getUnitStatus().getReputation());
						health = Math.round(activeUnit.getUnitStatus().getHealth());
					}
					String task = "";
					UnitTask unitTask = activeUnit.getTask();
					if (null != unitTask) {
						task = unitTask.getTask();
					}
					selectedUnitStatusBar = new UnitStatusBar(name, initTexture, mood, reputation, health, task, codeLines);
				}
				StatusBar statusBar = new StatusBar(mycodeLines, unitStatusBars, selectedUnitStatusBar);
				String json = new Gson().toJson(statusBar);
				response.getWriter().println(json);
			}
		}
	}

}

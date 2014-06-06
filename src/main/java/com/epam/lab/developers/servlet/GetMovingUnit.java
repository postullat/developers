package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.epam.lab.developers.servlet.json.UnitJson;
import com.google.gson.Gson;


@WebServlet("/get_moving_unit")
public class GetMovingUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMovingUnit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User user = LoginData.userLogined(request);

		if (null != user) {

			Game game = DataHolder.getInstance().getGame(user);
			if (null != game) {
				List<Team> teams = game.getTeams();
				List<UnitJson> unitsJson = new ArrayList<>();

				for (Team team : teams) {
					unitsJson.addAll(UnitJson.parseUnitListToJsonUnitList(team
							.getUnits()));
				}

				Unit unit = user.getTeam().getActiveUnit();
				
				UnitJson selectedUnit = null;
				if (null != unit) {
					selectedUnit = new UnitJson(unit.getCurrentTextureId(),
							unit.getId(), unit.getX(), unit.getY(),
							unit.getRotationAngle());
				}
				Gson gson = new Gson();
				Object[] unitsAndSelectedUnit = new Object[] { unitsJson,
						selectedUnit };
				
				String jsonUnits = gson.toJson(unitsAndSelectedUnit);
				PrintWriter writer = response.getWriter();
				writer.write(jsonUnits);
				writer.close();
			}
		}
	}

}

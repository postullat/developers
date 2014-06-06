package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
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
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.GameMap;
import com.epam.lab.developers.game.map.algorithm_way.Step;
import com.epam.lab.developers.game.map.algorithm_way.WaveAlgorithm;
import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.object.active.ActiveObject;
import com.epam.lab.developers.game.map.object.passive.Floor;
import com.epam.lab.developers.game.map.object.passive.PassiveObject;
import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.game.map.unit.UnitTask;
import com.google.gson.Gson;

/**
 * Servlet implementation class CoordinateStep
 */
@WebServlet("/mouse-manage")
public class MouseManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MouseManage() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int finishXCoord, StartXCoord;
		int finishYCoord, StartYCoord;
		int finishX;
		int finishY;
		int startY = 0;
		int startX = 0;
		String message = request.getParameter("message");
		if (null != message) {
			User user = LoginData.userLogined(request);
			if (null != user) {
				Game game = DataHolder.getInstance().getGame(user);
				if (null != game) {
					GameMap gameMap = game.getMap();
					int[][] mapBinary = game.getMapBinary();
					Team team = user.getTeam();
					if (message.contentEquals("right")) {
						Unit activeUnit = team.getActiveUnit();
						String responseMessage = "";
						Set<String> menuSet = new HashSet<>();
						// чи є якийсь активний юніт
						if (activeUnit != null) {
							startX = activeUnit.getX()
									/ gameMap.getFrameWidth();
							startY = activeUnit.getY()
									/ gameMap.getFrameHeight();
							finishXCoord = Integer.parseInt(request
									.getParameter("f_x"));
							finishYCoord = Integer.parseInt(request
									.getParameter("f_y"));
							finishX = finishXCoord / gameMap.getFrameWidth();
							finishY = finishYCoord / gameMap.getFrameHeight();
							MapObject mapObject = null;
							if (finishX < gameMap.getRows() - 1 && finishY < gameMap.getColumns() - 1) {
								mapObject = gameMap.getMapObjects()[finishX][finishY];
							}
							if (mapObject instanceof PassiveObject) {
								PassiveObject passiveObject = (PassiveObject) mapObject;
								responseMessage = "hide_menu";
								team.setSelectedActiveObject(null); // натиснуто
																	// на
																	// пасивний
																	// об'єкт
								if (passiveObject instanceof Floor) {
									// запустити алгоритм

									WaveAlgorithm algorithm = new WaveAlgorithm(
											mapBinary, startY, startX, finishY,
											finishX, gameMap.getFrameWidth(),
											gameMap.getFrameHeight());
									String str = algorithm.Algorithm();
									if (str.equals("good")) {
										LinkedList<Step> way = algorithm
												.getWay();
										
										activeUnit.setTask(new UnitTask(passiveObject, "move", way));

									}
								}
								// натиснуто правою кнопкою на активний об'єкт
							} else if (mapObject instanceof ActiveObject) {

								ActiveObject selectedActiveObject = (ActiveObject) mapObject;
								// встановити активний об'єкт
								team.setSelectedActiveObject(selectedActiveObject);
								/* формування меню для обєкта */
								responseMessage = "show_menu";

								menuSet.addAll(activeUnit.getAvailableMethods(selectedActiveObject));
								menuSet.addAll(selectedActiveObject.getAvailableMethods(activeUnit));
								// натиснуто правою кнопкою на підлогу
								// (запускаєм алгоритм шляху)
							}
						} else {
							responseMessage = "hide_menu";
						}
						Object[] menuData = new Object[] { responseMessage,
								menuSet };
						String json = new Gson().toJson(menuData);
						response.getWriter().println(json);
					}
					if (message.contentEquals("left")) {
						StartXCoord = Integer.parseInt(request
								.getParameter("s_x"));
						StartYCoord = Integer.parseInt(request
								.getParameter("s_y"));
						StartXCoord = StartXCoord / gameMap.getFrameWidth();
						StartYCoord = StartYCoord / gameMap.getFrameHeight();
						MapObject mapObject = null;
						if (StartXCoord < gameMap.getRows() - 1 && StartYCoord < gameMap.getColumns() - 1) {
							// чи це активний об'єкт
							mapObject = gameMap.getMapObjects()[StartXCoord][StartYCoord];
						}
						if (mapObject instanceof ActiveObject) {
							ActiveObject selectedActiveObject = (ActiveObject) mapObject;
							// встановити активний об'єкт
							team.setSelectedActiveObject(selectedActiveObject);
							// якщо це пасивний об'єкт, наприклад підлога, то
							// зняти виділення з об'єкту
						} else {
							team.setActiveUnit(null);
							team.setSelectedActiveObject(null);
						}
						for (Unit unit : user.getTeam().getUnits()) {
							if ((unit.getX() / gameMap.getFrameWidth() == StartXCoord)
									&& (unit.getY() / gameMap.getFrameWidth() == StartYCoord)) {
								user.getTeam().setActiveUnit(unit);
								response.getWriter().println(
										new Gson().toJson(unit));
								break;
							}
						}
					}
				}
			}
		}
	}
}

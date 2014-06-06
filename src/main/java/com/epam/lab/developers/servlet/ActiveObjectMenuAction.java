package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.util.LinkedList;

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
import com.epam.lab.developers.game.map.object.active.ActiveObject;
import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.game.map.unit.UnitTask;

/**
 * Servlet implementation class MenuAction
 */
@WebServlet("/active-object-menu-action")
public class ActiveObjectMenuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActiveObjectMenuAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int startX;
		int startY;
		int finishX;
		int finishY;
		String task = request.getParameter("task");
		if (null != task) {
			User user = LoginData.userLogined(request);
			if (null != user) {
				Game game = DataHolder.getInstance().getGame(user);
				if (null != game) {
					GameMap gameMap = game.getMap();
					int[][] mapBinary = game.getMapBinary();
					Team team = user.getTeam();
					Unit activeUnit = team.getActiveUnit();
					ActiveObject selectedActiveObject = team
							.getSelectedActiveObject();
					if (null != activeUnit && null != selectedActiveObject) {
						startX = activeUnit.getX() / gameMap.getFrameWidth();
						startY = activeUnit.getY() / gameMap.getFrameHeight();
						
						finishX = selectedActiveObject.getI();
						finishY = selectedActiveObject.getJ();
						int[] finishStep = deleteLastStep(finishX, finishY, mapBinary);
						finishX = finishStep[0];
						finishY = finishStep[1];
						// запустити алгоритм
						WaveAlgorithm algorithm = new WaveAlgorithm(
								mapBinary, startY, startX, finishY,
								finishX, gameMap.getFrameWidth(), gameMap.getFrameHeight() );
						String str = algorithm.Algorithm();
						if (str.equals("good")) {
							LinkedList<Step> way = algorithm.getWay();
							activeUnit.setTask(new UnitTask(selectedActiveObject, task, way));
						} else if (str.equals("too_close")) {
							activeUnit.setTask(new UnitTask(selectedActiveObject, task, new LinkedList<Step>()));
						}
						team.setSelectedActiveObject(null);
					}
				}
			}
		}
	}
	/* для видалення останнього кроку в шляху*/
	private int[] deleteLastStep(int finishX, int finishY, int[][] mapBinary) {
		int [] finishStep = new int[2];
		if(mapBinary[finishY][finishX]!=0){
			if(mapBinary[finishY+1][finishX]==0) finishY = finishY+1; 
			else if(mapBinary[finishY-1][finishX]==0) finishY = finishY-1;
			else if(mapBinary[finishY][finishX+1]==0) finishX = finishX+1;
			else if(mapBinary[finishY][finishX-1]==0) finishX = finishX-1;
			
		}
		finishStep[0] = finishX;
		finishStep[1] = finishY;
		return finishStep;
	}

}

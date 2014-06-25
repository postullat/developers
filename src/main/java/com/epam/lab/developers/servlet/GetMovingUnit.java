package com.epam.lab.developers.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.servlet.json.UnitJson;
import com.google.gson.Gson;

@Controller
@RequestMapping("/get_moving_unit")
public class GetMovingUnit{

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String getMovingUnit(@RequestParam String command, HttpServletRequest request) {

		User user = LoginData.userLogined(request);

		if (user != null) {

			Game game = DataHolder.getInstance().getGame(user);
			if (game != null && command.equals("give_me_moving_units")) {
				
				List<Team> teams = game.getTeams();
				List<UnitJson> unitsJson = new ArrayList<>();

				for (Team team : teams) {
					unitsJson.addAll(UnitJson.parseUnitListToJsonUnitList(team.getUnits()));
				}

				Unit unit = user.getTeam().getActiveUnit();

				UnitJson selectedUnit = null;
				if (unit != null) {
					selectedUnit = new UnitJson(unit.getCurrentTextureId(), unit.getId(), unit.getX(), unit.getY(), unit.getRotationAngle());
				}
				
				Object[] unitsAndSelectedUnit = new Object[] { unitsJson, selectedUnit };

				String json = new Gson().toJson(unitsAndSelectedUnit); 
				return json;
			}
		}
		
		return "{'error':'User object or game object equals null'}";
	}

}

package com.epam.lab.developers.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.domain.User;
import com.epam.lab.developers.game.Team;
import com.google.gson.Gson;

@Controller
@RequestMapping("/get_unit")
public class GetUnit {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String getUnit(HttpServletRequest request) {
		
		User user = LoginData.userLogined(request);
		if (user != null) {

			List<Team> teams = DataHolder.getInstance().getGame(user).getTeams();
			String jsonUnits = new Gson().toJson(teams);

			return jsonUnits;

		}
		
		return "{'error':'Can not get units'}";
	}
}

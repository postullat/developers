package com.epam.lab.developers.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.domain.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.servlet.json.UserWinLooseJson;
import com.google.gson.Gson;

@Controller
@RequestMapping("/finish-game")
public class FinishGame {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	String showResult(@RequestParam String command, HttpServletRequest request, HttpServletResponse response) {

		User user = LoginData.userLogined(request);
		if (user != null) {

			Game game = DataHolder.getInstance().getGame(user);

			if (game != null && command.equals("getWinLoose")) {

				UserWinLooseJson winLoose = new UserWinLooseJson();
				winLoose.setUserWin(game.getUserWin().getName());
				winLoose.setUserLoose(game.getUserLoose().getName());

				String json = new Gson().toJson(winLoose);

				return json;

			}

		}
		
		return "{error:user object equal null or game object equals null}";
	}

}

package com.epam.lab.developers.servlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.epam.lab.developers.servlet.json.GameJson;
import com.epam.lab.developers.servlet.json.UserJson;
import com.google.gson.Gson;


@Controller
@RequestMapping("/get-game-data")
public class GetGameData {

	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String getData(@RequestParam String command, @RequestParam String gameName, HttpServletRequest request) {
	
		
		
		if(command!=null){
			User user = LoginData.userLogined(request);
			if (null != user) {
				if (command.equals("get_game_info")) {
					Game gameByName = DataHolder.getInstance().getGameByName(gameName);
					if (null != gameByName) {
						GameJson gameJson = DataHolder.getInstance().getGameJson(gameByName);
						String json = new Gson().toJson(gameJson);
						return json;
					}
				}
				Game game = DataHolder.getInstance().getGame(user);
				if (game != null && command.equals("get_players")) {
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
						return json;
					}
					if (command.equals("get_game_name")) { // отримати назву гри
						String json = new Gson().toJson(game.getName());
						
						return json;
					}
			}
		}
		
		return "{'error':'incorrect command name:"+command+"'}";
	}

}

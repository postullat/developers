package com.epam.lab.developers.servlet;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.MyUserDetails;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.google.gson.Gson;

@Controller
@RequestMapping("/timer-waiting")
public class TimerWaitingForGame {
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String doPost(Principal principal, HttpServletRequest request) {
		
		User user = ((MyUserDetails) ((Authentication) principal).getPrincipal()).getUser();
		if (null != user) {
			Game game = DataHolder.getInstance().getGame(user);
			if (null != game) {
				
				return new Gson().toJson(game.isRunningGame());
			}
		}
		
		return "{'error':'user or game object equals null'}";
	}

}

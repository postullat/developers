package com.epam.lab.developers.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.google.gson.Gson;

@Controller
@RequestMapping("/list-of-games")
public class ListOfGames{
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String getAvailableListOfGames(HttpServletRequest request) {
		
		return new Gson().toJson(DataHolder.getInstance().getGamesToJoin());
	}

}

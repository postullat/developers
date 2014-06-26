package com.epam.lab.developers.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;
import com.google.gson.Gson;

@Controller
@RequestMapping("/count-user-online")
public class CountUserOnline {
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String getCountUserOnline(){ 
		
		
		Map<HttpSession, User> map = DataHolder.getInstance().getUserSessions();
		List<User> userList = new ArrayList<>(map.values());
		List<String> nickNames = new ArrayList<>();
		
		for(User user: userList){
			nickNames.add(user.getName());
		}
		 return new Gson().toJson(nickNames);
	}

}

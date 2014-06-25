package com.epam.lab.developers.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.lab.developers.data.DataHolder;

@Controller
@RequestMapping("/logout")
public class Logout {
	
	@RequestMapping(method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DataHolder.getInstance().getUserSessions().remove(session);
		
		return "view/home";
	}

}

package com.epam.lab.developers.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.lab.developers.data.DataHolder;

/**
 * Handle user requests and redirect to the jsp page
 * 
 * @author Volodymyr_Bondarchuk
 *
 */

@Controller
@RequestMapping("/" + HomeController.VIEW_NAME)
public class HomeController {

	protected static final String VIEW_NAME = "home";

	@RequestMapping(method = RequestMethod.GET)
	public String showPage(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		if (DataHolder.getInstance().getUserSessions().containsKey(session)) {
			return "view/connect";
		}

		return "view/" + VIEW_NAME;
	}

}

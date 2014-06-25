package com.epam.lab.developers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handle user requests and redirect to the jsp page
 * 
 * @author Volodymyr_Bondarchuk
 *
 */

@Controller
@RequestMapping("/" + ConnectController.VIEW_NAME)
public class ConnectController {

	protected static final String VIEW_NAME = "connect";

	@RequestMapping(method = RequestMethod.GET)
	public String showPage() {
		return "view/" + VIEW_NAME;
	}

}

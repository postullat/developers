package com.epam.lab.developers.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.lab.developers.entity.MyUserDetails;

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
	
	@Autowired
    private SessionRegistry sessionRegistry;  

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPage(ModelAndView model) {
		
		List<Object> principals = sessionRegistry.getAllPrincipals();
		List<String> usersOnline = new LinkedList<String>();
		
		for(Object principal : principals){
			usersOnline.add(((MyUserDetails)principal).getUsername());
		}
		
		model.setViewName("view/" + VIEW_NAME);
		model.addObject("usersOnline", usersOnline);
		
		return model;
	}

}

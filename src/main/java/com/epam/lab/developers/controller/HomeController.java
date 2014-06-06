package com.epam.lab.developers.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.developers.data.DataHolder;

@WebServlet("/" + HomeController.VIEW_NAME)
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected static final String VIEW_NAME = "home";
    
   public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(DataHolder.getInstance().getUserSessions().containsKey(session)) {
			String url = "/WEB-INF/view/connect.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		else{
			String url = "/WEB-INF/view/" + VIEW_NAME + ".jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

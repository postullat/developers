package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;
import com.google.gson.Gson;

@WebServlet("/count-user-online")
public class CountUserOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CountUserOnline() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<HttpSession, User> map = DataHolder.getInstance().getUserSessions();
		List<User> userList = new ArrayList<>(map.values());
		List<String> name = new ArrayList<>();
		
		for(User user: userList){
			name.add(user.getName());
		}
		response.getWriter().println(new Gson().toJson(name));
		
	}

}

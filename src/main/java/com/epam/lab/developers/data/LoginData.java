package com.epam.lab.developers.data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.lab.developers.entity.User;

public class LoginData {

	public static User userLogined(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return DataHolder.getInstance().getUserSessions().get(session);
	}
	
}

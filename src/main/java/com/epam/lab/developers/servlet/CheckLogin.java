package com.epam.lab.developers.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.domain.User;
import com.google.gson.Gson;

@Controller
public class CheckLogin {

	@RequestMapping(value = "/check-login", method = RequestMethod.POST)
	public @ResponseBody
	String checkUserLogin(@RequestParam String userName, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (isLogined(request, session)) {

			if (userName.equals("unknown")) {

				User user = DataHolder.getInstance().getUserSessions().get(session);
				return new Gson().toJson(user);
			}
		}

		return new Gson().toJson(getUserByNick(userName));
	}

	private boolean isLogined(HttpServletRequest request, HttpSession session) {
		return DataHolder.getInstance().getUserSessions().containsKey(session);
	}

	private User getUserByNick(String nick) {

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getByName(nick);

		return user;
	}

}

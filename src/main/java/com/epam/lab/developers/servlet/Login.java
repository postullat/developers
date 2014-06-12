package com.epam.lab.developers.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.setting.PropertyUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static final Logger logger = Logger.getLogger(Login.class);

	static {
		 PropertyConfigurator.configure(new Login().getClass().getResource(
			    "/log4j.properties"));
	}


	public Login() {
		super();
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name_login");
		String password = request.getParameter("password_login");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getByName(name);
		
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Such login doesn't exists");
			logger.debug("login " + name + " doesn't exists");
		} else {
			// перевірка правильності паролю
			if (!user.getPassword().contentEquals(
					Register.MD5Generate(password))) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println("You input wrong password");
				logger.debug("You input wrong password: "
						+ Register.MD5Generate(password));
			} else {
				boolean isUserExists = false;
				for (User user2 : DataHolder.getInstance().getUserSessions()
						.values()) {// перевіряє чи такий користувач
									// вже залогінений
					if (user2.getName().equals(name)) {
						isUserExists = true;
						break;
					}
				}
				if (isUserExists) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.getWriter().println("Such user is already logged in");
				} else {
					User playingUser = DataHolder.getInstance()
							.getPlayingUserByName(user.getName());
					HttpSession session = request.getSession();
					if (null != playingUser) {
						DataHolder.getInstance().getUserSessions()
								.put(session, playingUser);
					} else {
						DataHolder.getInstance().getUserSessions()
								.put(session, user);// створення нової сесії
					}
				}
			}
		}
	}

}

package com.epam.lab.developers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.validator.Validator;
import com.epam.lab.developers.validator.impl.CredentialValidator;
import com.epam.lab.developers.validator.impl.ValidationResult;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static final Logger logger = Logger.getLogger(Login.class);

	static {
		PropertyConfigurator.configure(new Login().getClass().getResource("/log4j.properties"));
	}

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Validator validator = new CredentialValidator();
		ValidationResult result = new ValidationResult();

		result = validator.validate(request);

		if (result.isSuccess()) {
			User user = (User)result.getValidatedData();
			HttpSession session = request.getSession();
			DataHolder.getInstance().getUserSessions().put(session, user);
		} else {

			String message = result.getErrorMessage();
			if (message != null) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println(message);
				logger.debug(message.replaceAll("<br>", ""));
			}
		}

	}

}

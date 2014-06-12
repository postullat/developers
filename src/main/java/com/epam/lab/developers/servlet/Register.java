package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;

import org.apache.log4j.Logger;

@WebServlet("/register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String NAME = "name";
	private static final String PASSWORD = "password";
	private static final String REPEATED_PASSWORD = "r_password";
	private static final String EMAIL = "email";
	private static final String DEFAULT_USER_PHOTO = "user_no_avatar.png";
	

	static final Logger logger = Logger.getLogger(Register.class);

	public Register() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p = 0;
		/* отримування даних з форми реєстрації */
		String name = request.getParameter(NAME);
		String password = request.getParameter(PASSWORD);
		String rPassword = request.getParameter(REPEATED_PASSWORD);
		String eMail = request.getParameter(EMAIL);
		String photo = DEFAULT_USER_PHOTO;

		UserDAO userDAO = new UserDAO();
		/* перевірка паролів */
		if (password.contentEquals(rPassword) && !rPassword.contentEquals("")) {
			p += 1;

		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Passwords mismatch");
			logger.debug("Passwords mismatch pass1=" + MD5Generate(password) + "pass2=" + MD5Generate(rPassword));
		}
		User user = userDAO.getByName(name);
		/* перевірка чи вже зареєстрований такий користувач */
		if (user == null && !name.contentEquals("")) {
			p += 1;
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Such login already exists");
			logger.debug("login " + name + " already exists");
		}
		/* валідація мила */
		if (validateMail(eMail)) {
			p += 1;
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Such email doesn t correct");
			logger.debug("Such email doesn t correct " + eMail);
		}
		/* добавляння користувача до бд */
		if (p == 3) {
			// System.out.println(MD5Generate(password));
			User validatedUser = new User(name, MD5Generate(password), eMail, photo);
			new UserDAO().insert(validatedUser);

			HttpSession session = request.getSession();
			DataHolder.getInstance().getUserSessions().put(session, validatedUser);
			p = 0;
		}

	}

	/**
	 * @param pass
	 *            - пароль у стрічковому форматі
	 * @throws NoSuchAlgorithmException
	 *             не існуючий алгоритм
	 * @return згенерований пароль алгоритмом md5
	 * */
	public static String MD5Generate(String pass) {
		String hashpass = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(pass.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			hashpass = number.toString(16);
			while (hashpass.length() < 32) {
				hashpass = "0" + hashpass;
			}
			return hashpass;
		} catch (NoSuchAlgorithmException e) {
			logger.debug("error in MD5 algorithm " + e);
		}
		return hashpass;
	}

	/**
	 * @param eMail
	 *            - адреса пошти
	 * @return true - адреса правильна; false - адреса не правильна
	 * */
	private boolean validateMail(String eMail) {
		Pattern p = Pattern.compile("[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*[a-zA-Z]");
		Matcher m = p.matcher(eMail);
		return m.matches();
	}

}

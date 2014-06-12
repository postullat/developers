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

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.entity.User;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static final Logger logger = Logger.getLogger(Register.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int p = 0;
		/* отримування даних з форми реєстрації */
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String rPassword = request.getParameter("r_password");
		String eMail = request.getParameter("email");
		String photo = "user_no_avatar.png";
		User user = null;
		UserDAO userDAO = new UserDAO();
		/* перевірка паролів */
		if (password.contentEquals(rPassword) && !rPassword.contentEquals("")) {
			p += 1;

		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Passwords mismatch");
			logger.debug("Passwords mismatch pass1=" + MD5Generate(password)
					+ "pass2=" + MD5Generate(rPassword));
		}
		user = userDAO.getByName(name);
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
			new UserDAO().insert(new User(name, MD5Generate(password), eMail,
					photo));

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
			 logger.debug("error in MD5 algorithm "+e);
		}
		return hashpass;
	}

	/**
	 * @param eMail
	 *            - адреса пошти
	 * @return true - адреса правильна; false - адреса не правильна
	 * */
	private boolean validateMail(String eMail) {
		Pattern p = Pattern
				.compile("[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*[a-zA-Z]");
		Matcher m = p.matcher(eMail);
		return m.matches();
	}

}

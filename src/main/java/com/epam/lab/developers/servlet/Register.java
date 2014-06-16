package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.enums.RequestDataType;
import com.epam.lab.developers.validator.Validator;
import com.epam.lab.developers.validator.impl.EmailValidator;
import com.epam.lab.developers.validator.impl.PasswordValidator;
import com.epam.lab.developers.validator.impl.UserNameValidator;
import com.epam.lab.developers.validator.impl.ValidationResult;

@WebServlet("/register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_USER_PHOTO = "user_no_avatar.png";

	static final Logger logger = Logger.getLogger(Register.class);

	public Register() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Set<Validator> validators = new LinkedHashSet<Validator>();
		List<ValidationResult> results = new LinkedList<ValidationResult>();

		validators.add(new UserNameValidator());
		validators.add(new PasswordValidator());
		validators.add(new EmailValidator());

		for (Validator validator : validators) {
			results.add(validator.validate(request));
		}

		if (allFieldsPopulatedCorrectly(results)) {
			User validatedUser = getUserFromValidationResults(results);
			new UserDAO().insert(validatedUser);

			HttpSession session = request.getSession();
			DataHolder.getInstance().getUserSessions().put(session, validatedUser);
		} else {
			for(ValidationResult result : results){
				
				String message = result.getErrorMessage();
				if(message!=null){
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.getWriter().println(message);
					logger.debug(message.replaceAll("<br>", ""));
				}
			}
		}



	}

	public static String generateMD5(String pass) {
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

	private User getUserFromValidationResults(List<ValidationResult> results) {

		User user = new User();

		for (ValidationResult result : results) {
			if (RequestDataType.USER_NAME == result.getDataType()) {
				user.setName((String)result.getValidatedData());
			}
			if (RequestDataType.PASSWORD == result.getDataType()) {
				user.setPassword(generateMD5((String)result.getValidatedData()));
			}
			if (RequestDataType.EMAIL == result.getDataType()) {
				user.setInfo(user.new Info((String)result.getValidatedData(), DEFAULT_USER_PHOTO));
			}
		}

		return user;

	}

	private boolean allFieldsPopulatedCorrectly(List<ValidationResult> results) {

		for (ValidationResult result : results) {
			if (!result.isSuccess()) {
				return false;
			}
		}

		return true;
	}

}

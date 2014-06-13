package com.epam.lab.developers.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.enums.RequestDataType;
import com.epam.lab.developers.validator.Validator;

public class UserNameValidator extends Validator {

	private static final String USER_NAME = "name";
	private static final String EMPTY_STRING = "";
	private static final int MIN_USER_LENGTH = 3;
	private static final int MAX_USER_LENGTH = 10;

	@Override
	public ValidationResult validate(HttpServletRequest request) {

		ValidationResult result = new ValidationResult();

		String userNameFromRequest = request.getParameter(USER_NAME);
		UserDAO userDAO = new UserDAO();
		User dbUser = userDAO.getByName(userNameFromRequest);

		if (dbUser != null) {
			result.setSuccess(false);
			result.setErrorMessage("Such user with nickname " + userNameFromRequest
					+ " is already registered in system. Please try to input another nickname.<br><br>");
			
			return result;
		}
		if (userNameFromRequest.trim().isEmpty() && userNameFromRequest.trim().equals(EMPTY_STRING)) {
			result.setSuccess(false);
			result.setErrorMessage("User name can not be empty. It should be more than 3 and less than 10 characters.<br><br>");
			
			return result;
		}
		if (userNameFromRequest.length()<MIN_USER_LENGTH || userNameFromRequest.length()>MAX_USER_LENGTH) {
			result.setSuccess(false);
			result.setErrorMessage("User name should be more than 3 and less than 10 characters.<br><br>");
			
			return result;
		}
		
		Pattern p = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9]*");
		Matcher m = p.matcher(userNameFromRequest);
		
		
		if (!m.matches()) {
			result.setSuccess(false);
			result.setErrorMessage("User name should starts from a-z or A-Z characters and can't contain special symbols like '!,?@#$%^&*'.<br><br>");
			
			return result;
		}
		
		result.setSuccess(true);
		result.setValidatedData(userNameFromRequest);
		result.setDataType(RequestDataType.USER_NAME);

		return result;
	}

}

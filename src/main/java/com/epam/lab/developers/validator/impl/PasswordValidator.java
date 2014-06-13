package com.epam.lab.developers.validator.impl;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.developers.enums.RequestDataType;
import com.epam.lab.developers.validator.Validator;

public class PasswordValidator extends Validator {

	private static final String PASSWORD = "password";
	private static final String REPEATED_PASSWORD = "r_password";
	private static final String EMPTY_STRING = "";
	private static final int MIN_PASSWORD_LENGTH = 3;
	private static final int MAX_PASSWORD_LENGTH = 25;

	@Override
	public ValidationResult validate(HttpServletRequest request) {

		ValidationResult result = new ValidationResult();

		String password = request.getParameter(PASSWORD);
		String repeatedPassword = request.getParameter(REPEATED_PASSWORD);

		if (!password.equals(repeatedPassword)) {
			result.setSuccess(false);
			result.setErrorMessage("The passwords you entered do not match.<br><br>");
			
			return result;
		}
		if (isPasswordsEmpty(password, repeatedPassword)) {
			result.setSuccess(false);
			result.setErrorMessage("The password can not be empty.<br><br>");
			
			return result;
		}

		if (password.length()<MIN_PASSWORD_LENGTH || password.length()>MAX_PASSWORD_LENGTH) {
			result.setSuccess(false);
			result.setErrorMessage("Passwords must be at least 3 character and less than 25.<br><br>");
			
			return result;
		}

		result.setSuccess(true);
		result.setValidatedData(password);
		result.setDataType(RequestDataType.PASSWORD);

		return result;
	}

	private boolean isPasswordsEmpty(String password, String repeatedPassword) {
		return password.equals(EMPTY_STRING) || repeatedPassword.equals(EMPTY_STRING) || password.isEmpty() || repeatedPassword.isEmpty();
	}

}

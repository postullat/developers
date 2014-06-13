package com.epam.lab.developers.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.developers.enums.RequestDataType;
import com.epam.lab.developers.validator.Validator;

public class EmailValidator extends Validator {

	private static final String EMAIL = "email";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public ValidationResult validate(HttpServletRequest request) {

		ValidationResult result = new ValidationResult();

		String email = request.getParameter(EMAIL);

		Pattern p = Pattern.compile(EMAIL_PATTERN);
		Matcher m = p.matcher(email);

		if (!m.matches()) {
			result.setSuccess(false);
			result.setErrorMessage("Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.<br><br>");

			return result;
		}

		result.setSuccess(true);
		result.setValidatedData(email);
		result.setDataType(RequestDataType.EMAIL);

		return result;
	}

}

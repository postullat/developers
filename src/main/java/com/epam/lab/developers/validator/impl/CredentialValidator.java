package com.epam.lab.developers.validator.impl;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.domain.User;
import com.epam.lab.developers.enums.RequestDataType;
import com.epam.lab.developers.servlet.Register;
import com.epam.lab.developers.validator.Validator;

public class CredentialValidator extends Validator {

	private static final String EMPTY_STRING = "";
	private static final String LOGIN_NAME = "name_login";
	private static final String PASSWORD = "password_login";

	@Override
	public ValidationResult validate(HttpServletRequest request) {

		ValidationResult result = new ValidationResult();

		String loginName = request.getParameter(LOGIN_NAME);
		String password = request.getParameter(PASSWORD);


		if(loginName.equals(EMPTY_STRING) || loginName.isEmpty()){
			result.setSuccess(false);
			result.setErrorMessage("User NAME can not be EMPTY.<br><br>");

			return result;
		}
		
		if(password.equals(EMPTY_STRING) || password.isEmpty()){
			result.setSuccess(false);
			result.setErrorMessage("User PASSWORD can not be EMPTY.<br><br>");

			return result;
		}
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getByName(loginName);
		if (user == null) {
			result.setSuccess(false);
			result.setErrorMessage("User with nickname '" + loginName + "' doesn't exists in DB.<br><br>");

			return result;
		}

		if (!user.getPassword().equals(Register.generateMD5(password))) {
			result.setSuccess(false);
			result.setErrorMessage("User nickname exists in DB, but PASSWORD doesn't match.<br><br>");

			return result;
		}
		
		for(User onlineUser : DataHolder.getInstance().getUserSessions().values()){
			if (onlineUser.getName().equals(loginName)) {
				result.setSuccess(false);
				result.setErrorMessage("User '"+loginName+"' is already logged in system.<br><br>");
				
				return result;
			}
		}

		result.setSuccess(true);
		result.setValidatedData(user);
		result.setDataType(RequestDataType.USER);

		return result;
	}

}

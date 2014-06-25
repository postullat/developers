package com.epam.lab.developers.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.domain.User;
import com.epam.lab.developers.validator.Validator;
import com.epam.lab.developers.validator.impl.CredentialValidator;
import com.epam.lab.developers.validator.impl.ValidationResult;

@Controller
@RequestMapping("/login")
public class Login {

	static final Logger logger = Logger.getLogger(Login.class);

	static {
		PropertyConfigurator.configure(new Login().getClass().getResource("/log4j.properties"));
	}

	@RequestMapping(method = RequestMethod.POST)
	protected @ResponseBody ResponseEntity<String> logginUser(HttpServletRequest request) {

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
				logger.debug(message.replaceAll("<br>", ""));
				return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}

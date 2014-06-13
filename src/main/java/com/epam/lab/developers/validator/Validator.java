package com.epam.lab.developers.validator;

import javax.servlet.http.HttpServletRequest;
import com.epam.lab.developers.validator.impl.ValidationResult;

public abstract class Validator {

	public abstract ValidationResult validate(HttpServletRequest request);
}

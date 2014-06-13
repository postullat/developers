package com.epam.lab.developers.validator.impl;

import com.epam.lab.developers.enums.RequestDataType;

public class ValidationResult {

	private boolean isSuccess;
	private String errorMessage;
	private String validatedData;
	private RequestDataType dataType;
	
	public boolean isSuccess() {
		return isSuccess;
	}
	
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getValidatedData() {
		return validatedData;
	}
	
	public void setValidatedData(String validatedData) {
		this.validatedData = validatedData;
	}

	public RequestDataType getDataType() {
		return dataType;
	}

	public void setDataType(RequestDataType dataType) {
		this.dataType = dataType;
	}

}

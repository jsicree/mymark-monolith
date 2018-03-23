package com.mymark.ws;

public enum ApiErrorCode {
	INVALID_VALUE("InvalidValue"),
	EMPTY_VALUE("EmptyValue"),
	API_EXCEPTION("ApiException"),
	ILLEGAL_ARGUMENT_EXCEPTION("IllegalArgumentException");
	
	private String code;
	
	ApiErrorCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}

package com.mymark.ws;

public enum ApiErrorCode {
	ACCESS_DENIED("AccessDenied"),
	CUSTOMER_DOES_NOT_EXIST("CustomerDoesNotExist"),
	INVALID_VALUE("InvalidValue"),
	NULL_OR_EMPTY_VALUE("NullOrEmptyValue"),
	SERVICE_EXCEPTION("ApiException"),
	UNKNOWN_COUNTRY_CODE("UnknownCountryCode"), 
	UNKNOWN_STATE_CODE("UnknownStateCode"), 
	USERNAME_EXISTS("UserNameExists");
	
	private String code;
	
	ApiErrorCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}

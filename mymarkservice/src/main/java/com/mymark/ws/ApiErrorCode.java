package com.mymark.ws;

public enum ApiErrorCode {
	INVALID_VALUE("InvalidValue"),
	EMPTY_VALUE("EmptyValue"),
	SERVICE_EXCEPTION("ApiException");
	
	private String code;
	
	ApiErrorCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}

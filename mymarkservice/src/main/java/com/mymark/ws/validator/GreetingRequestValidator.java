package com.mymark.ws.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mymark.api.GreetingRequest;
import com.mymark.ws.ApiErrorCode;
import com.mymark.ws.ApiMessages;

@Component
public class GreetingRequestValidator implements Validator {
	
	protected final static Logger log = LoggerFactory
			.getLogger(GreetingRequestValidator.class);
	

	public GreetingRequestValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return GreetingRequest.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GreetingRequest request = (GreetingRequest) target;

		log.debug("Validating greeting request.");
		if (request.getName() != null && request.getName().isEmpty()) {
			errors.rejectValue("name", ApiErrorCode.EMPTY_VALUE.getCode(), ApiMessages.EMPTY_VALUE_MSG);			
		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", ApiErrorCode.NULL_OR_EMPTY_VALUE.getCode(), ApiMessages.NULL_OR_EMPTY_VALUE_MSG);
		
	}

}

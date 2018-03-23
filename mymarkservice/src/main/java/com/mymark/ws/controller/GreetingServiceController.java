package com.mymark.ws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mymark.api.GreetingDto;
import com.mymark.api.GreetingResponse;
import com.mymark.app.data.reference.Language;
import com.mymark.app.service.GreetingService;
import com.mymark.app.service.ServiceException;
import com.mymark.ws.ApiException;
import com.mymark.ws.ApiMessages;

/**
 * Handles requests for the form page examples.
 */
@RestController
@RequestMapping("api/greeting")
public class GreetingServiceController {

	@Autowired
	protected GreetingService greetingService;

	@Autowired
	protected MessageSource messageSource;

	protected final static Logger log = LoggerFactory.getLogger(GreetingServiceController.class);

	public GreetingServiceController() {

	}

	@RequestMapping(value = "/{langCode}/hello", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GreetingResponse> sayHello(@PathVariable(required = true) String langCode,
			@RequestParam(required = false) String name) throws ApiException {
		log.info("In hello...");
		log.debug("/hello params: langCode = " + langCode + "; name = " + name);
		Language language = null;
		if (langCode != null) {

			try {
				language = Language.valueOf(langCode.toUpperCase());
			} catch (IllegalArgumentException | NullPointerException e) {
				throw new IllegalArgumentException("Language code " + langCode + " is not supported.");
			}

		}

		GreetingResponse response = new GreetingResponse();

		GreetingDto greeting = new GreetingDto();

		try {
			if (name != null && name.isEmpty() == false) {
				if (name.equalsIgnoreCase("BAD_NAME")) {
					throw new ApiException("Request contained name " + name);
				} else {
					greeting.setMessage(greetingService.sayHello(language, name));
				}
			} else {
				greeting.setMessage(greetingService.sayHello(language));
			}
			response.setGreeting(greeting);
		} catch (ServiceException e) {
			log.error("ServiceException thrown while creating customer.", e);
			e.printStackTrace();
			throw new ApiException(ApiMessages.API_EXCEPTION_MSG, e);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
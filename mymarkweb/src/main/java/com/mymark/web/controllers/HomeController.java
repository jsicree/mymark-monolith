package com.mymark.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	protected final static Logger log = LoggerFactory
			.getLogger(HomeController.class);


	public HomeController() {

	}

	@RequestMapping(value = "/home")
	public String home() {
		log.info("Displaying the home page.");

		return "home";
	}

}
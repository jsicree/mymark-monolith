package com.mymark.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	protected final static Logger log = LoggerFactory
			.getLogger(WelcomeController.class);

		@GetMapping("/")
		public String welcome(Model model) {
			log.info("Displaying the welcome page.");
		    model.addAttribute("message", "Hello Spring MVC 5!");
			return "welcome";
		}
		
}
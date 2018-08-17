package com.mymark.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

	protected final static Logger log = LoggerFactory
			.getLogger(RegisterController.class);

		@GetMapping("/register")
		public String welcome(Model model) {
			log.info("Displaying the register page.");
			return "register";
		}
		
}
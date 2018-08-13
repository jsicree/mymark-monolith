package com.mymark.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	protected final static Logger log = LoggerFactory
			.getLogger(HomeController.class);

		@GetMapping("/home")
		public String home(Model model) {
			log.info("Displaying the home page.");
		    model.addAttribute("message", "Welcome to Fantasy Island!");
			return "home";
		}
		
}
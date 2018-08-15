package com.mymark.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

	protected final static Logger log = LoggerFactory
			.getLogger(CheckoutController.class);

		@GetMapping("/checkout")
		public String welcome(Model model) {
			log.info("Displaying the checkout page.");
			return "checkout";
		}
		
}
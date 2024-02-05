package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = "/")
	public String showWelcomePage() {
		return "welcome";
	}	
}

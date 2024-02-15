package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/headmaster_dashboard")
public class HeadmasterController {

	@GetMapping
	public ModelAndView getWelcomeMessage(Principal principal) {
		String name = principal.getName();
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.setViewName("headmaster/headmaster_dashboard");
		return mav;
	}

}

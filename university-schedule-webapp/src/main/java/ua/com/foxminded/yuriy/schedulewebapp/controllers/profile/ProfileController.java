package ua.com.foxminded.yuriy.schedulewebapp.controllers.profile;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile_dashboard")
public class ProfileController {

	@GetMapping
	public ModelAndView getWelcomeMessage(Principal principal) {
		String name = principal.getName();
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.setViewName("profile/profile_dashboard");
		return mav;
	}

}

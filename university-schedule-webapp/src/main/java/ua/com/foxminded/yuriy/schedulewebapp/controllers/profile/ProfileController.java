package ua.com.foxminded.yuriy.schedulewebapp.controllers.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/profile/dashboard")
@AllArgsConstructor
public class ProfileController {

	@GetMapping
	public ModelAndView getWelcomeMessage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile/dashboard");
		return mav;
	}
}

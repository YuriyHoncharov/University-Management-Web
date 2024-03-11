package ua.com.foxminded.yuriy.schedulewebapp.controllers.profile;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import lombok.AllArgsConstructor;


@Controller
@RequestMapping("/profile/dashboard")
@AllArgsConstructor
public class HeadmasterController {

	

	@GetMapping
	public ModelAndView getWelcomeMessage(Principal principal) {
		
		String name = principal.getName();

		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);

		mav.setViewName("profile/dashboard");
		return mav;
	}
}

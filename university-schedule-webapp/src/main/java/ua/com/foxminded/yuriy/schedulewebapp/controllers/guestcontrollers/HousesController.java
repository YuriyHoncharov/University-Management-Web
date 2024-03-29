package ua.com.foxminded.yuriy.schedulewebapp.controllers.guestcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;

@Controller
@RequestMapping("/houses")
@AllArgsConstructor
public class HousesController {

	private HouseService houseService;

	@GetMapping
	public String getAllHouses(Model model) {
		
		model.addAttribute("houses", houseService.getAll());
		return "houses_info";
	}
}

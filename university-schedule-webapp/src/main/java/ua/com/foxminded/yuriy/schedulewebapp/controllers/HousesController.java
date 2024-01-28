package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;

@Controller
public class HousesController {

	private HouseService houseService;

	@Autowired
	public HousesController(HouseService houseService) {
		this.houseService = houseService;
	}

	@GetMapping(value = "/houses")
	public String showHousesPage(Model model) {
		model.addAttribute("houses", houseService.getAll());
		return "houses";
	}
}

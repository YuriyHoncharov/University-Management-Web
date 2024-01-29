package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;

@Controller
@RequestMapping("/houses")
public class HousesController {

	private HouseService houseService;

	@Autowired
	public HousesController(HouseService houseService) {
		this.houseService = houseService;
	}

	@GetMapping
	public String getAllHouses(Model model) {
		model.addAttribute("houses", houseService.getAll());
		return "houses";
	}
}

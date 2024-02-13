package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;

@Controller
@RequestMapping("/professors")
public class ProfessorsController {

	@Autowired
	private ProfessorService professorService;

	@GetMapping
	public String getAllProfessors(Model model) {
		model.addAttribute("professors", professorService.getAll());
		return "professors";
	}
}

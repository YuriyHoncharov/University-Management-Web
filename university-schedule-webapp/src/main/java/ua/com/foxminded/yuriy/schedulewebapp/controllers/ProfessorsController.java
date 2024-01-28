package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;

@Controller
public class ProfessorsController {

	private ProfessorService professorService;

	@Autowired
	public ProfessorsController(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@GetMapping(value = "/professors")
	public String showProfessorsPage(Model model) {
		model.addAttribute("professors", professorService.getAll());
		return "professors";
	}
}

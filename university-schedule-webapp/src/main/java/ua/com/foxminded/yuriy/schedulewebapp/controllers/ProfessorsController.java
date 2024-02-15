package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;

@Controller
@RequestMapping("/professors")
@AllArgsConstructor
public class ProfessorsController {

	private ProfessorService professorService;

	@GetMapping
	public String getAllProfessors(Model model) {
		model.addAttribute("professors", professorService.getAll());
		return "professors";
	}
}

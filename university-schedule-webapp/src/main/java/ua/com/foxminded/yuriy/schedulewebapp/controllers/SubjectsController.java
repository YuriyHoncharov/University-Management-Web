package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;

@Controller
public class SubjectsController {

	private SubjectService subjectService;

	@Autowired
	public SubjectsController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@GetMapping(value = "/subjects")
	public String showSubjectsPage(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "subjects";
	}
}

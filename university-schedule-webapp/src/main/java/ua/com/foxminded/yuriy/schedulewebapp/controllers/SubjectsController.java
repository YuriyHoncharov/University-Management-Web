package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;

@Controller
@RequestMapping("/subjects")
public class SubjectsController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public String getAllSubjects(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "subjects";
	}
}

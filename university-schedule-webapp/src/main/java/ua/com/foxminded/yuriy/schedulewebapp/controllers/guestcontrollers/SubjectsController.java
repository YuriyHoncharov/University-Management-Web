package ua.com.foxminded.yuriy.schedulewebapp.controllers.guestcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;

@Controller
@RequestMapping("/subjects")
@AllArgsConstructor
public class SubjectsController {

	private SubjectService subjectService;

	@GetMapping
	public String getAllSubjects(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "subjects_info";
	}
}

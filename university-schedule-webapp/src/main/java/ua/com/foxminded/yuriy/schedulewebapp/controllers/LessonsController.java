package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;

@Controller
public class LessonsController {

	private LessonService lessonService;

	@Autowired
	public LessonsController(LessonService lessonService) {
		this.lessonService = lessonService;
	}

	@GetMapping(value = "/lessons")
	public String showLessonsPage(@RequestParam(name = "studentId", required = false) Long studentId, Model model) {
		model.addAttribute("lessons", lessonService.getByWizardId(studentId != null ? studentId : 5L));
		return "lessons";
	}
}

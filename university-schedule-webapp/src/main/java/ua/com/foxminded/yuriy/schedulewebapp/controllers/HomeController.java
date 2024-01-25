package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;

@Controller
public class HomeController {

	private ProfessorService teacherService;
	private SubjectService subjectService;
	private HouseService houseService;
	private LessonService lessonService;

	@Autowired
	public HomeController(ProfessorService teacherService, SubjectService subjectService, HouseService houseService,
			LessonService lessonService) {
		this.teacherService = teacherService;
		this.subjectService = subjectService;
		this.houseService = houseService;
		this.lessonService = lessonService;
	}

	@GetMapping(value = "/")
	public String showWelcomePage() {
		return "welcome";
	}

	@GetMapping(value = "/teachers")
	public String showProfessorsPage(Model model) {
		model.addAttribute("teachers", teacherService.getAll());
		return "teachers";

	}

	@GetMapping(value = "/subjects")
	public String showSubjectsPage(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "subjects";
	}

	@GetMapping(value = "/houses")
	public String showHousesPage(Model model) {
		List<House>houses = houseService.getAll();
		model.addAttribute("houses", houses);
		return "houses";
	}

	@GetMapping(value = "/lessons")
	public String showLessonsPage(Model model) {
		model.addAttribute("lessons", lessonService.getAll());
		return "lessons";
	}
}

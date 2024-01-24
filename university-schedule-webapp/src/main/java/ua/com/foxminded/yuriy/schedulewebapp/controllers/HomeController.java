package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Grade;
import ua.com.foxminded.yuriy.schedulewebapp.service.GradeService;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.TeacherService;

@Controller
public class HomeController {

	private TeacherService teacherService;
	private SubjectService subjectService;
	private GradeService gradeService;
	private LessonService lessonService;

	@Autowired
	public HomeController(TeacherService teacherService, SubjectService subjectService, GradeService gradeService,
			LessonService lessonService) {
		this.teacherService = teacherService;
		this.subjectService = subjectService;
		this.gradeService = gradeService;
		this.lessonService = lessonService;
	}

	@GetMapping(value = "/")
	public String showWelcomePage() {
		return "welcome";
	}

	@GetMapping(value = "/teachers")
	public String showTeachersPage(Model model) {
		model.addAttribute("teachers", teacherService.getAll());
		return "teachers";

	}

	@GetMapping(value = "/subjects")
	public String showSubjectsPage(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "subjects";
	}

	@GetMapping(value = "/grades")
	public String showGradesPage(Model model) {
		List<Grade>grades = gradeService.getAll();
		model.addAttribute("grades", grades);
		return "grades";
	}

	@GetMapping(value = "/lessons")
	public String showLessonsPage(Model model) {
		model.addAttribute("lessons", lessonService.getAll());
		return "lessons";
	}
}

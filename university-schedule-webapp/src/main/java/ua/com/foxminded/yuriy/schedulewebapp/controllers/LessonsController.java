package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.StudentService;

@Controller
@RequestMapping("/lessons")
public class LessonsController {

	private LessonService lessonService;
	private ProfessorService professorService;
	private StudentService studentService;

	@Autowired
	public LessonsController(LessonService lessonService, ProfessorService professorService,
			StudentService studentService) {
		this.lessonService = lessonService;
		this.professorService = professorService;
		this.studentService = studentService;
	}

	@GetMapping
	public String getAllWizardLessonsByDate(@RequestParam(name = "selectedDate", required = false) String selectedDate,
			@RequestParam(name = "userId", required = false) Long userId, Model model) {

		try {
			Student student = studentService.getById(userId)
					.orElseThrow(() -> new RuntimeException("Student not found"));

			List<LessonDto> lessonDtos = lessonService
					.getByWizardIdAndDate(userId , selectedDate).stream().map(LessonDto::new)
					.collect(Collectors.toList());
			model.addAttribute("lessons", lessonDtos);
		} catch (RuntimeException studentException) {
			try {
				Professor professor = professorService.getById(userId)
						.orElseThrow(() -> new RuntimeException("Professor not found"));

				List<LessonDto> lessonDtos = lessonService
						.getByWizardIdAndDate(userId , selectedDate).stream().map(LessonDto::new)
						.collect(Collectors.toList());
				model.addAttribute("lessons", lessonDtos);
			} catch (RuntimeException professorException) {

				model.addAttribute("error", "User not found. Please enter a valid ID");
			}
		}

		return "lessons";
	}
}

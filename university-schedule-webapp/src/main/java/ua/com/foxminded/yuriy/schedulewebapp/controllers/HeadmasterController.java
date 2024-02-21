package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.StudentService;

@Controller
@RequestMapping("/headmaster/dashboard")
@AllArgsConstructor
public class HeadmasterController {

	private StudentService studentService;
	private ProfessorService professorService;
	private HouseService houseService;
	private LessonService lessonService;

	@GetMapping
	public ModelAndView getWelcomeMessage(Principal principal) {
		String name = principal.getName();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);

		List<House> houses = houseService.getAll();
		List<StudentDto> students = studentService.getAll();
		List<ProfessorDto> professors = professorService.getAll();
		List<LessonDto> lessons = lessonService.getAllLessonsDto();
		
		
		mav.addObject("houses", houses);
		mav.addObject("students", students);
		mav.addObject("professors", professors);
		mav.addObject("lessons", lessons);

		mav.setViewName("headmaster/dashboard");
		return mav;
	}
}

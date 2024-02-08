package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;

@Controller
@RequestMapping("/lessons")
public class LessonsController {

	private LessonService lessonService;

	@Autowired
	public LessonsController(LessonService lessonService) {
		this.lessonService = lessonService;
	}

	@GetMapping
	public String getAllWizardLessonsByDate(@RequestParam(name = "selectedDay", required = false) String selectedDay,
			@RequestParam(name = "studentId", required = false) Long studentId, Model model) {

		LocalDateTime selectedDateStamp = null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (selectedDay == null || selectedDay.isEmpty()) {
			selectedDateStamp = LocalDateTime.now();
		} else {
			System.out.println("Received selectedDay: " + selectedDay);
			selectedDateStamp = LocalDate.parse(selectedDay, formatter).atStartOfDay();
		}
		if (studentId >= 5) {
			List<LessonDto> lessonsDtos = lessonService
					.getByWizardIdAndFilters(studentId != null ? studentId : 5L, selectedDateStamp).stream()
					.map(LessonDto::new).collect(Collectors.toList());
			model.addAttribute("lessons", lessonsDtos);

		} else {
			List<LessonDto> lessonsDtos = lessonService
					.getByProfessorIdAndDate(studentId != null ? studentId : 5L, selectedDateStamp).stream()
					.map(LessonDto::new).collect(Collectors.toList());
			model.addAttribute("lessons", lessonsDtos);
		}
		return "lessons";
	}
}

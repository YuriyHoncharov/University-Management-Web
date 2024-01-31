package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.yuriy.schedulewebapp.Dto.LessonDto;
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
	public String getAllWizardLessons(@RequestParam(name = "studentId", required = false) Long studentId, Model model) {
		List<LessonDto> lessonDtos = lessonService.getByWizardId(studentId != null ? studentId : 5L).stream()
				.map(LessonDto::new).collect(Collectors.toList());
		model.addAttribute("lessons", lessonDtos);
		return "lessons";
	}

	@GetMapping("/byDay")
	public String getAllWizardLessonsByDay(@RequestParam(name = "selectedDay", required = false) String selectedDay, Long studentId,
			Model model) {
		int dayOfWeek = DayOfWeek.valueOf(selectedDay.toUpperCase()).getValue();
		List<LessonDto> lessonsDtos = lessonService
				.getByWizardIdAndDayOfWeek(studentId != null ? studentId : 5L, dayOfWeek).stream().map(LessonDto::new)
				.collect(Collectors.toList());
		model.addAttribute("lessons", lessonsDtos);
		return "lessons";
	}
}

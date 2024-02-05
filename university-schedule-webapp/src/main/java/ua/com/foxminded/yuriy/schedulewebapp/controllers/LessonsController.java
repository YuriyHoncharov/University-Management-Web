package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
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
	public String getAllWizardLessons(@RequestParam(name = "studentId", required = false) Long studentId, Model model) {
		List<LessonDto> lessonDtos = lessonService.getByWizardId(studentId != null ? studentId : 5L).stream()
				.map(LessonDto::new).collect(Collectors.toList());
		model.addAttribute("lessons", lessonDtos);
		return "lessons";
	}

	@GetMapping("/byDay")
	public String getAllWizardLessonsByDay(@RequestParam(name = "selectedDay", required = false) String selectedDay,
			Long studentId, Model model) {
		int dayOfWeek = DayOfWeek.valueOf(selectedDay.toUpperCase()).getValue();
		List<LessonDto> lessonsDtos = lessonService
				.getByWizardIdAndDayOfWeek(studentId != null ? studentId : 5L, dayOfWeek).stream().map(LessonDto::new)
				.collect(Collectors.toList());
		model.addAttribute("lessons", lessonsDtos);
		model.addAttribute("selectedDay", selectedDay);
		return "lessons";
	}

	@GetMapping("/byDate")
	public String getAllWizardLessonsByDate(@RequestParam(name = "selectedDay", required = false)String selectedDay, Long studentId, Model model) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date parsedData = dateFormat.parse(selectedDay);
			Timestamp seleTimestamp = new Timestamp(parsedData.getTime());
			List<LessonDto>lessonsDtos = lessonService.getByWizardIdAndDate(studentId != null ? studentId : 5L, seleTimestamp).stream().map(LessonDto::new).collect(Collectors.toList());
			model.addAttribute("lessons", lessonsDtos);
			return "lessons";
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
		}
	}
}

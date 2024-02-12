package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
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
	public String getAllWizardLessonsByDate(@RequestParam(name = "selectedDate", required = false) String selectedDate,
			@RequestParam(name = "userId", required = false) Long userId, Model model) {

		try {
			List<LessonDto> lessonDtos = lessonService.getByWizardIdAndDate(userId != null ? userId : 5L, selectedDate)
					.stream().map(LessonDto::new).collect(Collectors.toList());
			model.addAttribute("lessons", lessonDtos);
		} catch (UserNotFoundException e) {
			model.addAttribute("error", "User not found. Please enter a valid ID");
		}
		return "lessons";
	}
}

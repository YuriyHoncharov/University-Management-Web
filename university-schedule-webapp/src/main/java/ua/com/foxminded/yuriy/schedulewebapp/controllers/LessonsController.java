package ua.com.foxminded.yuriy.schedulewebapp.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;

@Controller
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonsController {

	private LessonService lessonService;

	@GetMapping
	public String getAllWizardLessonsByDate(@RequestParam(name = "selectedDate", required = false) String selectedDate,
			@RequestParam(name = "userId", required = false) Long userId, Model model) {

		try {
			List<LessonDto> lessons = lessonService.getByWizardIdAndDate(userId != null ? userId : 5L, selectedDate);
			model.addAttribute("lessons", lessons);
		} catch (UserNotFoundException e) {
			model.addAttribute("error", "User not found. Please enter a valid ID");
		}
		return "lessons";
	}

//	@GetMapping
//	public ModelAndView getUpdateForm(@PathVariable Long id) {
//		ModelAndView mav = new ModelAndView();
//		return lessonService.getById(id).map(lesson -> {
//			mav.addObject("lesson", lesson);
//			mav.setViewName("headmaster_dashboard/lesson/edit");
//			return mav;
//		}).orElseGet(() -> {
//			mav.setViewName("redirect:/headmaster_dashboard/lesson");
//			return mav;
//		});
//	}

}

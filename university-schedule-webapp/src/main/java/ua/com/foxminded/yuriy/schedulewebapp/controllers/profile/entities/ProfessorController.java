package ua.com.foxminded.yuriy.schedulewebapp.controllers.profile.entities;

import java.util.List;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import lombok.AllArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;

@Controller
@RequestMapping("/profile/dashboard/professors")
@AllArgsConstructor
public class ProfessorController {

	private ProfessorService professorService;
	private SubjectService subjectService;

	@GetMapping
	public ModelAndView getProfessorPage(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
		Page<ProfessorDto> professors = professorService.getAll(PageRequest.of(page, 2));
		ModelAndView mav = new ModelAndView();
		mav.addObject("professors", professors);
		mav.addObject("numbers", IntStream.range(1, professors.getTotalPages()).toArray());
		mav.setViewName("profile/entities/professors");
		return mav;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProfessor(@PathVariable Long id) {
		try {
			professorService.delete(id);
			return new ResponseEntity<>("Professor deleted successfully", HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("Failed to delete professor " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditView(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		List<Subject> availableSubjects = subjectService.findAllUnassignedSubjects();
		return professorService.getById(id).map(professor -> {
			mav.addObject("actualSubject", professor.getSubject());
			mav.addObject("availableSubjects", availableSubjects);
			mav.addObject("professor", professor);
			mav.setViewName("profile/entities/edit/professorEdit");
			return mav;
		}).orElseGet(() -> {
			mav.setViewName("redirect:profile/entities/professors");
			return mav;
		});
	}

	@PutMapping("update/{id}")
	public ResponseEntity<Object> update(@RequestBody Professor professor, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(professorService.save(professorService.professorBuilder(professor, id)));
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/create")
	public ModelAndView showCreateView() {
		ModelAndView mav = new ModelAndView();

		List<Subject> availableSubjects = subjectService.findAllUnassignedSubjects();
		mav.addObject("availableSubjects", availableSubjects);
		mav.setViewName("profile/entities/create/professorCreate");
		return mav;

	}

	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody Professor professor) {
		try {
			return ResponseEntity
					.ok(professorService.save(professorService.professorBuilder(professor, professor.getId())));

		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

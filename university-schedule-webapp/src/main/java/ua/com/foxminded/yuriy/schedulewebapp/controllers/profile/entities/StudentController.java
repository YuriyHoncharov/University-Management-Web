package ua.com.foxminded.yuriy.schedulewebapp.controllers.profile.entities;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.RoleService;
import ua.com.foxminded.yuriy.schedulewebapp.service.StudentService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.YearService;

@Controller
@RequestMapping("/profile/dashboard/students")
@AllArgsConstructor
public class StudentController {

	private StudentService studentService;
	private YearService yearService;
	private SubjectService subjectService;
	private HouseService houseService;
	private RoleService roleService;

	@GetMapping
	public ModelAndView pagination(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
		Page<StudentDto> pageStudent = studentService.getAllByPage(PageRequest.of(page, 10));
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageStudent", pageStudent);
		mav.addObject("numbers", IntStream.range(1, pageStudent.getTotalPages()).toArray());
		mav.setViewName("profile/entities/students");
		return mav;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		try {
			studentService.delete(id);
			return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting student : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditView(@PathVariable Long id) {

		ModelAndView mav = new ModelAndView();
		return studentService.getById(id).map(student -> {
			mav.addObject("years", yearService.getAll());
			mav.addObject("houses", houseService.getAll());
			mav.addObject("student", student);
			mav.setViewName("profile/entities/edit/studentEdit");
			return mav;
		}).orElseGet(() -> {
			mav.setViewName("redirect:profile/entities/students");
			return mav;
		});
	}

	@PutMapping("update/{id}")
	public ResponseEntity<Object> update(@RequestBody Student student, @PathVariable Long id) {
		try {
			Student existingStudent = studentService.getById(id).get();
			if (existingStudent != null) {
				Year year = yearService.getById(student.getYear().getId()).get();
				House house = houseService.getById(student.getHouse().getId()).get();
				existingStudent.setHouse(house);
				existingStudent.setYear(year);
				existingStudent.setName(student.getName());
				existingStudent.setLastName(student.getLastName());
			}
			Student updatedStudent = studentService.save(existingStudent);
			return ResponseEntity.ok(updatedStudent);

		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/edit/{id}/subjects")
	public ModelAndView showSubjectsEditPage(@PathVariable Long id) {

		ModelAndView mav = new ModelAndView();

		return studentService.getById(id).map(student -> {
			mav.addObject("student", student);
			mav.addObject("subjects", student.getSubjects());
			mav.addObject("availableSubjects", subjectService.getAll().stream()
					.filter(subject -> !student.getSubjects().contains(subject)).collect(Collectors.toList()));
			mav.setViewName("profile/entities/edit/subjectsEdit");
			return mav;
		}).orElseGet(() -> {
			mav.setViewName("redirect:profile/entities/edit/studentEdit");
			return mav;
		});
	}

	@PostMapping("/edit/{studentId}/subjects/{subjectId}")
	public ResponseEntity<String> editStudentSubjects(@PathVariable Long studentId, @PathVariable Long subjectId) {
		try {
			Optional<Student> optionalStudent = studentService.getById(studentId);

			if (optionalStudent.isPresent()) {
				Student student = optionalStudent.get();
				List<Subject> subjects = student.getSubjects();

				Optional<Subject> subjectToDelete = subjects.stream().filter(subject -> subject.getId().equals(subjectId))
						.findFirst();

				if (subjectToDelete.isPresent()) {
					// Subject found, delete it
					subjects.remove(subjectToDelete.get());
					studentService.save(student);
					return ResponseEntity.ok("Subject deleted successfully.");
				} else {
					// Subject not found, add it
					Subject subjectToAdd = subjectService.getById(subjectId).get();
					subjects.add(subjectToAdd);
					studentService.save(student);
					return ResponseEntity.ok("Subject added successfully");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error modifying subjects: " + e.getMessage());
		}
	}

	@GetMapping("/create")
	public ModelAndView showCreateView() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("years", yearService.getAll());
		mav.addObject("houses", houseService.getAll());
		mav.setViewName("profile/entities/create/studentCreate");
		return mav;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody Student student) {
		try {
			student.setRole(roleService.getById((long) 2).get());
			Student createdStudent = studentService.save(student);
			return ResponseEntity.ok(createdStudent);

		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

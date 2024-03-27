package ua.com.foxminded.yuriy.schedulewebapp.repository.controller.profile.entities;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.com.foxminded.yuriy.schedulewebapp.controllers.profile.entities.StudentController;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.RoleService;
import ua.com.foxminded.yuriy.schedulewebapp.service.StudentService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.YearService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	@Mock
	private StudentService studentService;
	@Mock
	private YearService yearService;
	@Mock
	private SubjectService subjectService;
	@Mock
	private HouseService houseService;
	@Mock
	private RoleService roleService;
	@InjectMocks
	private StudentController studentController;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void should_show_students_view() throws Exception {
		Page<StudentDto> pageStudent = Mockito.mock(Page.class);
		when(studentService.findAll(any())).thenReturn(pageStudent);
		mockMvc.perform(get("/profile/dashboard/students")).andExpect(status().isOk())
				.andExpect(model().attribute("pageStudent", pageStudent))
				.andExpect(model().attribute("numbers", IntStream.range(1, pageStudent.getTotalPages()).toArray()))
				.andExpect(view().name("profile/entities/students"));
	}

	@Test
	void should_delete_student() throws Exception {
		Long studentId = 1L;
		mockMvc.perform(delete("/profile/dashboard/students/delete/{id}", studentId)).andExpect(status().isOk())
				.andExpect(content().string("Student deleted successfully"));
		verify(studentService, times(1)).delete(studentId);
	}

	@Test
	void should_not_delete_student() throws Exception {
		Long studentId = 1L;
		String errorMessage = String.format("Student with following ID : %d is not present in DataBase", studentId);
		when(studentService.delete(studentId)).thenThrow(new UserNotFoundException(errorMessage));
		mockMvc.perform(delete("/profile/dashboard/students/delete/{id}", studentId))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Error deleting student : " + errorMessage));
		verify(studentService, times(1)).delete(studentId);
	}

	@Test
	void should_show_edit_view() throws Exception {
		Long studentId = 1L;
		List<Year> years = new ArrayList<>();
		List<House> houses = new ArrayList<>();
		Student student = new Student();
		when(studentService.getById(studentId)).thenReturn(Optional.of(student));
		when(yearService.getAll()).thenReturn(years);
		when(houseService.getAll()).thenReturn(houses);
		mockMvc.perform(get("/profile/dashboard/students/edit/{id}", studentId)).andExpect(status().isOk())
				.andExpect(view().name("profile/entities/edit/studentEdit")).andExpect(model().attribute("years", years))
				.andExpect(model().attribute("houses", houses)).andExpect(model().attribute("student", student));
	}

	@Test
	void should_update_Student() throws Exception {

		Long studentId = 1L;
		Student student = new Student();
		Year year = new Year();
		year.setId(1L);
		House house = new House();
		house.setId(1L);
		student.setName("testName");
		student.setLastName("testLastName");
		student.setYear(year);
		student.setHouse(house);
		String studentJson = objectMapper.writeValueAsString(student);
		when(studentService.getById(studentId)).thenReturn(Optional.of(student));
		when(yearService.getById(1L)).thenReturn(Optional.of(year));
		when(houseService.getById(1L)).thenReturn(Optional.of(house));
		mockMvc.perform(put("/profile/dashboard/students/update/{id}", studentId).contentType(MediaType.APPLICATION_JSON)
				.content(studentJson)).andExpect(status().isOk());
		verify(studentService, times(1)).save(student);
	}

	@Test
	void should_show_subject_editPage() throws Exception {
		Long studentId = 1L;
		Student student = new Student();
		List<Subject> subjects = new ArrayList<>();
		student.setSubjects(subjects);
		List<Subject> availableSubjects = new ArrayList<>();

		when(studentService.getById(studentId)).thenReturn(Optional.of(student));
		when(subjectService.getAll()).thenReturn(availableSubjects);

		mockMvc.perform(get("/profile/dashboard/students/edit/{id}/subjects", studentId)).andExpect(status().isOk())
				.andExpect(view().name("profile/entities/edit/subjectsEdit"))
				.andExpect(model().attribute("student", student)).andExpect(model().attribute("student", student))
				.andExpect(model().attribute("availableSubjects", availableSubjects.stream()
						.filter(subject -> !student.getSubjects().contains(subject)).collect(Collectors.toList())));
		verify(studentService, times(1)).getById(studentId);
		verify(subjectService, times(1)).getAll();
	}

	@Test
	void should_edit_student_subject_when_subject_to_delete_is_present() throws Exception {

		Long studentId = 1L;
		Long subjectIdToDelete = 100L;
		Student student = new Student();
		Subject subjectToDelete = new Subject();
		subjectToDelete.setId(subjectIdToDelete);
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subjectToDelete);
		student.setSubjects(subjects);

		when(studentService.getById(studentId)).thenReturn(Optional.of(student));

		mockMvc
				.perform(post("/profile/dashboard/students/edit/{studentId}/subjects/{subjectId}", studentId,
						subjectIdToDelete))
				.andExpect(status().isOk()).andExpect(content().string("Subject deleted successfully."));

		assertEquals(0, student.getSubjects().size());
		verify(studentService, times(1)).save(student);
	}

	@Test
	void should__not_edit_student_subject_when_subject_to_delete_is_not_present() throws Exception {

		Long studentId = 1L;
		Long subjectIdToDelete = 100L;
		Long subjectIdToAdd = 50L;
		Student student = new Student();
		Subject subjectToDelete = new Subject();
		subjectToDelete.setId(subjectIdToDelete);
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subjectToDelete);
		student.setSubjects(subjects);
		Subject subjectToAdd = new Subject();
		subjectToAdd.setId(subjectIdToAdd);

		when(studentService.getById(studentId)).thenReturn(Optional.of(student));
		when(subjectService.getById(subjectIdToAdd)).thenReturn(Optional.of(subjectToAdd));

		mockMvc
				.perform(
						post("/profile/dashboard/students/edit/{studentId}/subjects/{subjectId}", studentId, subjectIdToAdd))
				.andExpect(status().isOk()).andExpect(content().string("Subject added successfully"));

		assertEquals(2, student.getSubjects().size());
		verify(studentService, times(1)).save(student);
	}

	@Test
	void should_show_create_page_view() throws Exception {
		List<Year> years = new ArrayList<>();
		List<House> houses = new ArrayList<>();
		when(yearService.getAll()).thenReturn(years);
		when(houseService.getAll()).thenReturn(houses);
		mockMvc.perform(get("/profile/dashboard/students/create"))
		.andExpect(status().isOk())
				.andExpect(view().name("profile/entities/create/studentCreate"))
				.andExpect(model().attribute("years", years)).andExpect(model().attribute("houses", houses));
		verify(yearService, times(1)).getAll();
		verify(houseService, times(1)).getAll();
	}
	
	@Test
	void should_create_new_student() throws Exception {
		Student student = new Student();
		Role role = new Role();
		role.setId(2L);
		role.setName("STUDENT");
		Student savedStudent = new Student();
		String studentJson = objectMapper.writeValueAsString(student);
		when(roleService.getById(2L)).thenReturn(Optional.of(role));
		when(studentService.save(student)).thenReturn(student);
		mockMvc.perform(post("/profile/dashboard/students/create").contentType(MediaType.APPLICATION_JSON).content(studentJson)).andExpect(status().isOk());
		verify(roleService, times(1)).getById(2L);
		verify(studentService, times(1)).save(student);
	}

}

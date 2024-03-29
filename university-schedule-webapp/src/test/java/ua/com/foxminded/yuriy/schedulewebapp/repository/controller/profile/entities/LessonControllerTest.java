package ua.com.foxminded.yuriy.schedulewebapp.repository.controller.profile.entities;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import ua.com.foxminded.yuriy.schedulewebapp.controllers.profile.entities.LessonController;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Wizard;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.WizardRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.AuditoriumService;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.YearService;

@ExtendWith(MockitoExtension.class)
public class LessonControllerTest {

	@Mock
	private LessonService lessonService;
	@Mock
	private SubjectService subjectService;
	@Mock
	private AuditoriumService auditoriumService;
	@Mock
	private HouseService houseService;
	@Mock
	private ProfessorService professorService;
	@Mock
	private YearService yearService;
	@Mock
	private WizardRepository wizardRepository;
	@Mock
	private HttpServletRequest request;
	@Mock
	private Authentication authentication;
	@InjectMocks
	private LessonController lessonController;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void should_ReturnView_With_AllLessons_To_AdminUser() throws Exception {

		Page<LessonDto> lessonPage = Mockito.mock(Page.class);
		when(lessonService.getLessonsByFilters(null, 1)).thenReturn(lessonPage);
		mockMvc.perform(get("/profile/dashboard/lessons").principal(authentication).param("page", "1"))
				.andExpect(model().attribute("pageLessons", lessonPage))
				.andExpect(model().attribute("numbers", IntStream.range(1, lessonPage.getTotalPages()).toArray()))
				.andExpect(view().name("profile/entities/lessons"));
		verify(lessonService, times(1)).getLessonsByFilters(null, 1);
	}

	@Test
	void should_return_view_With_AllLessons_To_StudentUser_ByDate() throws Exception {

		Page<LessonDto> lessonPage = Mockito.mock(Page.class);		
		when(lessonService.getLessonsByFilters("2024-03-30", 0)).thenReturn(lessonPage);
		
		mockMvc.perform(get("/profile/dashboard/lessons").principal(authentication).param("selectedDate", "2024-03-30"))
				.andExpect(model().attribute("pageLessons", lessonPage))
				.andExpect(model().attribute("numbers", IntStream.range(1, lessonPage.getTotalPages()).toArray()))
				.andExpect(view().name("profile/entities/lessons"));
		verify(lessonService, times(1)).getLessonsByFilters("2024-03-30", 0);
	}

	@Test
	void should_delete_lesson() throws Exception {
		Long lessonId = 1L;
		mockMvc.perform(delete("/profile/dashboard/lessons/delete/{id}", lessonId)).andExpect(status().isOk())
				.andExpect(content().string("Lesson deleted successfully"));
		verify(lessonService, times(1)).delete(lessonId);
	}

	@Test
	void should_show_lessonEdit_view() throws Exception {
		Long lessonId = 1L;
		List<Subject> subjects = new ArrayList<>();
		List<ProfessorDto> professors = new ArrayList<>();
		List<Auditorium> auditoriums = new ArrayList<>();
		List<Year> years = new ArrayList<>();
		List<House> houses = new ArrayList<>();
		Lesson lesson = new Lesson();
		when(subjectService.getAll()).thenReturn(subjects);
		when(professorService.getAll()).thenReturn(professors);
		when(auditoriumService.getAll()).thenReturn(auditoriums);
		when(yearService.getAll()).thenReturn(years);
		when(houseService.getAll()).thenReturn(houses);
		when(lessonService.getById(lessonId)).thenReturn(Optional.of(lesson));

		mockMvc.perform(get("/profile/dashboard/lessons/edit/{id}", lessonId)).andExpect(status().isOk())
				.andExpect(model().attribute("subjects", subjects)).andExpect(model().attribute("professors", professors))
				.andExpect(model().attribute("auditoriums", auditoriums)).andExpect(model().attribute("years", years))
				.andExpect(model().attribute("houses", houses)).andExpect(model().attribute("lesson", lesson))
				.andExpect(view().name("profile/entities/edit/lessonEdit"));
	}

	@Test
	void should_update_lesson() throws Exception {
		Long lessonId = 1L;

		Lesson lesson = new Lesson();
		Subject subject = new Subject();
		subject.setId(1L);
		Professor professor = new Professor();
		professor.setId(1L);
		Auditorium auditorium = new Auditorium();
		auditorium.setId(1L);
		House house = new House();
		house.setId(1L);
		Year year = new Year();
		year.setId(1L);

		lesson.setProfessor(professor);
		lesson.setAuditorium(auditorium);
		lesson.setHouse(house);
		lesson.setYear(year);
		lesson.setSubject(subject);
		lesson.setId(1L);

		when(lessonService.lessonBuilder(lesson, lessonId)).thenReturn(lesson);

		String updatedLessonJson = objectMapper.writeValueAsString(lesson); // Convert to JSON

		mockMvc.perform(put("/profile/dashboard/lessons/update/{id}", lessonId).contentType(MediaType.APPLICATION_JSON)
				.content(updatedLessonJson)).andExpect(status().isOk());
		verify(lessonService, times(1)).save(lesson);
	}

	@Test
	void should_show_createView() throws Exception {
		List<Subject> subjects = new ArrayList<>();
		List<ProfessorDto> professors = new ArrayList<>();
		List<Auditorium> auditoriums = new ArrayList<>();
		List<Year> years = new ArrayList<>();
		List<House> houses = new ArrayList<>();
		when(subjectService.getAll()).thenReturn(subjects);
		when(professorService.getAll()).thenReturn(professors);
		when(auditoriumService.getAll()).thenReturn(auditoriums);
		when(yearService.getAll()).thenReturn(years);
		when(houseService.getAll()).thenReturn(houses);

		mockMvc.perform(get("/profile/dashboard/lessons/create"))
				.andExpect(view().name("profile/entities/create/lessonCreate"))
				.andExpect(model().attribute("subjects", subjects)).andExpect(model().attribute("professors", professors))
				.andExpect(model().attribute("auditoriums", auditoriums)).andExpect(model().attribute("years", years))
				.andExpect(model().attribute("houses", houses)).andExpect(status().isOk());
	}

	@Test
	void should_create_newLesson() throws Exception {
		Long lessonId = 1L;
		Lesson newLesson = new Lesson();
		newLesson.setId(lessonId);
		Professor professor = new Professor();
		professor.setId(1L);
		newLesson.setProfessor(professor);
		when(lessonService.lessonBuilder(newLesson, lessonId)).thenReturn(newLesson);
		String lessonJSON = objectMapper.writeValueAsString(newLesson);
		mockMvc
				.perform(
						post("/profile/dashboard/lessons/create").contentType(MediaType.APPLICATION_JSON).content(lessonJSON))
				.andExpect(status().isOk());
	}

	@Test
	void should_not_create_newLesson() throws Exception {
		Long lessonId = 1L;
		Lesson newLesson = new Lesson();
		newLesson.setId(lessonId);
		Professor professor = new Professor();
		professor.setId(1L);
		newLesson.setProfessor(professor);
		when(lessonService.lessonBuilder(newLesson, lessonId)).thenReturn(newLesson);
		when(lessonService.save(newLesson))
				.thenThrow(new ValidationException("Another Lesson is already assigned to this Auditorium for "));
		String lessonJSON = objectMapper.writeValueAsString(newLesson);
		mockMvc
				.perform(
						post("/profile/dashboard/lessons/create").contentType(MediaType.APPLICATION_JSON).content(lessonJSON))
				.andExpect(content().string("Another Lesson is already assigned to this Auditorium for "));

	}
}

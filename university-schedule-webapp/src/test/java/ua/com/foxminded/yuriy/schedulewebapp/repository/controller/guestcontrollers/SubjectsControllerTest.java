package ua.com.foxminded.yuriy.schedulewebapp.repository.controller.guestcontrollers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.foxminded.yuriy.schedulewebapp.controllers.guestcontrollers.SubjectsController;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SubjectsControllerTest {

	@Mock
	private SubjectService subjectService;

	@InjectMocks
	private SubjectsController subjectsController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(subjectsController).build();
	}

	@Test
	void shouldGetSubjectsPage() throws Exception {
		List<Subject> subjects = new ArrayList<>();
		subjects.add(new Subject());
		when(subjectService.getAll()).thenReturn(subjects);
		mockMvc.perform(get("/subjects")).andExpect(status().isOk()).andExpect(view().name("subjects_info"))
				.andExpect(model().attributeExists("subjects")).andExpect(model().attribute("subjects", subjects));

		verify(subjectService, times(1)).getAll();
	}
}

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.foxminded.yuriy.schedulewebapp.controllers.guestcontrollers.ProfessorsController;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProfessorControllerTest {

	@Mock
	private ProfessorService professorService;

	@InjectMocks
	private ProfessorsController professorController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
	}

	@Test
	void shouldGet_ProfessorsInfo_Page() throws Exception {
		List<ProfessorDto> professors = new ArrayList<>();
		professors.add(new ProfessorDto());
		when(professorService.getAll()).thenReturn(professors);
		mockMvc.perform(get("/professors")).andExpect(status().isOk()).andExpect(view().name("professors_info"))
				.andExpect(model().attributeExists("professors")).andExpect(model().attribute("professors", professors));

		verify(professorService, times(1)).getAll();
	}
}

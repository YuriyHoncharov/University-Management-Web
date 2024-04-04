package ua.com.foxminded.yuriy.schedulewebapp.repository.controller.profile.entities;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.foxminded.yuriy.schedulewebapp.controllers.profile.entities.ProfessorController;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.EntityNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.RoleService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class ProfessorControllerTest {
	@Mock
	private ProfessorService professorService;
	@Mock
	private SubjectService subjectService;
	@Mock
	private RoleService roleService;
	@InjectMocks
	private ProfessorController professorController;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void should_show_professor_list_page() throws Exception {
		Page<ProfessorDto> professorPage = Mockito.mock(Page.class);
		when(professorService.getAll(any())).thenReturn(professorPage);
		mockMvc.perform(get("/profile/dashboard/professors")).andExpect(status().isOk())
				.andExpect(model().attribute("professors", professorPage))
				.andExpect(model().attribute("numbers", IntStream.range(1, professorPage.getTotalPages()).toArray()))
				.andExpect(view().name("profile/entities/professors"));
		verify(professorService, times(1)).getAll(any());
	}

	@Test
	void should_delete_professor_when_professor_exist() throws Exception {
		Long professorId = 1L;
		when(professorService.delete(professorId)).thenReturn(professorId);
		mockMvc.perform(delete("/profile/dashboard/professors/delete/{id}", professorId)).andExpect(status().isOk())
				.andExpect(content().string("Professor deleted successfully"));
		verify(professorService, times(1)).delete(professorId);
	}

	@Test
	void should_not_delete_professor_if_not_exist() throws Exception {
		Long professorId = 1L;
		when(professorService.delete(professorId))
				.thenThrow(new EntityNotFoundException("with following Id : " + professorId));
		mockMvc.perform(delete("/profile/dashboard/professors/delete/{id}", professorId))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Failed to delete professor with following Id : 1"));
		verify(professorService, times(1)).delete(professorId);
	}

	@Test
	void should_show_edit_page_view() throws Exception {
		List<Subject> availableSubjects = new ArrayList<>();
		when(subjectService.findAllUnassignedSubjects()).thenReturn(availableSubjects);
		List<Subject> subjects = new ArrayList<>();
		Subject subject = new Subject();
		subjects.add(subject);
		Professor professor = new Professor();
		professor.setSubject(subject);
		Long professorId = 1L;
		when(professorService.getById(professorId)).thenReturn(Optional.of(professor));
		mockMvc.perform(get("/profile/dashboard/professors/edit/{id}", professorId)).andExpect(status().isOk())
				.andExpect(view().name("profile/entities/edit/professorEdit"))
				.andExpect(model().attribute("actualSubject", professor.getSubject()))
				.andExpect(model().attribute("availableSubjects", availableSubjects))
				.andExpect(model().attribute("professor", professor));
		verify(subjectService, times(1)).findAllUnassignedSubjects();
		verify(professorService, times(1)).getById(professorId);
	}

	@Test
	void should_update_professor() throws Exception {
		Long professorId = 1L;
		Professor existingProfessor = new Professor();
		existingProfessor.setId(professorId);
		existingProfessor.setName("Name");
		existingProfessor.setLastName("LastName");
		String professorJSON = objectMapper.writeValueAsString(existingProfessor);	
		
		when(professorService.professorBuilder(existingProfessor, professorId)).thenReturn(existingProfessor);	
		when(professorService.save(existingProfessor)).thenReturn(existingProfessor);
		mockMvc.perform(put("/profile/dashboard/professors/update/{id}", professorId)
				.contentType(MediaType.APPLICATION_JSON).content(professorJSON)).andExpect(status().isOk());
		verify(professorService, times(1)).save(existingProfessor);
		
	}

	@Test
	void should_show_create_view() throws Exception {
		List<Subject> subjects = new ArrayList<>();
		when(subjectService.findAllUnassignedSubjects()).thenReturn(subjects);
		mockMvc.perform(get("/profile/dashboard/professors/create")).andExpect(status().isOk())
				.andExpect(view().name("profile/entities/create/professorCreate"));
		verify(subjectService, times(1)).findAllUnassignedSubjects();
	}

	@Test
	void should_create_new_professor() throws Exception {
		Professor professor = new Professor();
		String professorJson = objectMapper.writeValueAsString(professor);
		Long professorId = 1L;
		Role role = new Role();
		role.setId(3L);
		role.setName("PROFESSOR");
		mockMvc.perform(
				post("/profile/dashboard/professors/create").contentType(MediaType.APPLICATION_JSON).content(professorJson))
				.andExpect(status().isOk());
	}

}

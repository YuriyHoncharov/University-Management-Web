package ua.com.foxminded.yuriy.schedulewebapp.repository.integrationalTests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.repository.ProfessorRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.RoleRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.SubjectRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		ProfessorRepository.class, RoleRepository.class, SubjectRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class ProfessorRepositoryIT {

	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	

	@Test
	void should_Save_Professor() {

		Professor teacher = new Professor();
		teacher.setLogin("newProfessor");
		teacher.setPassword("password");
		teacher.setName("New");
		teacher.setLastName("Professor");
		teacher.setRole(roleRepository.findById(3L).orElse(null));
		professorRepository.save(teacher);
		assertNotNull(teacher.getId());
	}

	@Test
	void should_Find_Professor_By_Id() {

		Long teacherId = 4L;
		Professor teacher = professorRepository.findById(teacherId).orElse(null);
		assertNotNull(teacher);
		assertEquals("Professor", teacher.getName());
	}

	@Test
	void should_Update_Professor() {

		Long teacherId = 4L;
		Professor teacher = professorRepository.findById(teacherId).orElse(null);
		assertNotNull(teacher);
		teacher.setName("UpdatedName");
		professorRepository.save(teacher);
		Professor updatedProfessor = professorRepository.findById(teacherId).orElse(null);
		assertNotNull(updatedProfessor);
		assertEquals("UpdatedName", updatedProfessor.getName());
	}

	@Test
	void should_Delete_Professor() {

		Long teacherId = 4L;
		professorRepository.deleteById(teacherId);
		assertNull(professorRepository.findById(teacherId).orElse(null));
	}
	
	@Test
	void shouldReturn_Professor_BySubject() {
		Long subjectId = 3L;
		Subject subject = subjectRepository.findById(subjectId).get();
		List<Subject>subjects = new ArrayList<>();
		subjects.add(subject);
		Professor professorBySubject = professorRepository.getBySubject(subjects);
		assertEquals("Professor", professorBySubject.getName());
		assertEquals("Three", professorBySubject.getLastName());
		assertEquals(6L, professorBySubject.getId());
	}
}

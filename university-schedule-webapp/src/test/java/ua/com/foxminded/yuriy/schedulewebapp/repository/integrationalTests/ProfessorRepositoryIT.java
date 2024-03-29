package ua.com.foxminded.yuriy.schedulewebapp.repository.integrationalTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private SubjectRepository subjectRepository;

	@Test
	void shouldReturn_Professor_BySubject() {
		Long subjectId = 3L;
		Optional<Subject>optionalSubject = subjectRepository.findById(subjectId);				
		if (optionalSubject.isPresent()) {
	        Subject subject = optionalSubject.get();
	        Professor professorBySubject = professorRepository.getBySubject(subject);
	        assertEquals("Three", professorBySubject.getLastName());
	        assertEquals(6L, professorBySubject.getId());
		}
	}
}

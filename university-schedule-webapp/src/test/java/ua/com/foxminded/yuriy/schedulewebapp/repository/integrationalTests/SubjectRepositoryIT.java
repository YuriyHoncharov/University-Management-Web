package ua.com.foxminded.yuriy.schedulewebapp.repository.integrationalTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.repository.SubjectRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		SubjectRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SubjectRepositoryIT {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Test
	void shouldReturn_UnassignedSubject() {
		List<Subject>unassignedSubjects = subjectRepository.findAllUnassignedSubjects();
		assertEquals(4L, unassignedSubjects.get(0).getId());
		assertEquals("UnassignedSubject", unassignedSubjects.get(0).getName());
	}
	
}

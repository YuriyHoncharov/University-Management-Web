package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		SubjectRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SubjectRepositoryIT {
	@Autowired
	private SubjectRepository subjectRepository;

	@Test
	public void testSaveSubject() {

		Subject newSubject = new Subject();
		newSubject.setName("Chemistry");
		newSubject.setDescription("Chemistry course");
		Subject savedSubject = subjectRepository.save(newSubject);
		assertEquals("Chemistry", savedSubject.getName());
		assertEquals("Chemistry course", savedSubject.getDescription());
	}

	@Test
	public void testFindById() {

		Long subjectId = 1L;
		Subject foundSubject = subjectRepository.findById(subjectId).orElse(null);
		assertEquals("Math", foundSubject.getName());
		assertEquals("Mathematics course", foundSubject.getDescription());
	}

	@Test
	public void testUpdateSubject() {

		Long subjectId = 1L;
		Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
		existingSubject.setName("Updated Math");
		existingSubject.setDescription("Updated Mathematics course");
		Subject updatedSubject = subjectRepository.save(existingSubject);
		assertEquals("Updated Math", updatedSubject.getName());
		assertEquals("Updated Mathematics course", updatedSubject.getDescription());
	}

	@Test
	public void testDeleteSubject() {

		Long subjectId = 1L;
		subjectRepository.deleteById(subjectId);
		assertFalse(subjectRepository.existsById(subjectId));
	}

	@Test
	public void testSubjectNotExistsAfterDeletion() {
		Long nonExistingSubjectId = 999L;
		boolean exists = subjectRepository.existsById(nonExistingSubjectId);
		assertFalse(exists);
	}
}

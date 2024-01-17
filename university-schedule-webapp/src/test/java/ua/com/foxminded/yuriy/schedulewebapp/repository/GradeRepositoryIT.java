package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Grade;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		GradeRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class GradeRepositoryIT {
	@Autowired
	private GradeRepository gradeRepository;

	@Test
	void shouldSaveGrade() {

		Grade grade = new Grade();
		grade.setValue("Z");
		Grade savedGrade = gradeRepository.save(grade);
		assertNotNull(savedGrade.getId());
		assertEquals("Z", savedGrade.getValue());
	}

	@Test
	void shouldFindGradeById() {

		Long gradeId = 1L;
		Grade foundGrade = gradeRepository.findById(gradeId).orElse(null);
		assertNotNull(foundGrade);
		assertEquals("A", foundGrade.getValue());
	}

	@Test
	void shouldUpdateGrade() {

		Long gradeId = 2L;
		Grade grade = gradeRepository.findById(gradeId).orElse(null);
		assertNotNull(grade);
		grade.setValue("UpdatedGrade");
		Grade updatedGrade = gradeRepository.save(grade);
		assertNotNull(updatedGrade.getId());
		assertEquals("UpdatedGrade", updatedGrade.getValue());
	}

	@Test
	void shouldDeleteGrade() {

		Long gradeId = 3L;
		gradeRepository.deleteById(gradeId);
		List<Grade> grades = gradeRepository.findAll();
		assertEquals(2, grades.size());
	}
}

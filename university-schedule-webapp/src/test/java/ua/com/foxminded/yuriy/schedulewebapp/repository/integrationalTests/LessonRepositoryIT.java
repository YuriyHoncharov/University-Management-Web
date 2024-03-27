package ua.com.foxminded.yuriy.schedulewebapp.repository.integrationalTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.repository.LessonRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		LessonRepository.class, StudentRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LessonRepositoryIT {

	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Test
	void shouldReturn_Lesson_ByDate() {
		LocalDate date = LocalDate.parse("2024-01-15");
		Pageable pageable = PageRequest.of(0, 10);
		Page<Lesson> lessonPage = lessonRepository.getByDate(date, pageable);
		assertEquals(1, lessonPage.getTotalElements());
	}

	@Test
	void shouldReturn_Lessons_ForStudent_ById() {
		Long id = 2L;
		Student student = studentRepository.findById(id).get();
		Page<Lesson> lessonPage = lessonRepository.getByStudentId(student.getHouse(), student.getYear(),
				student.getSubjects(), PageRequest.of(0, 10));
		assertEquals(1, lessonPage.getTotalElements());
		assertEquals("Math", lessonPage.getContent().get(0).getSubject().getName());
	}

	@Test
	void shouldReturn_Lesson_ForProfessor_ByDate() {
		Long id = 4L;
		LocalDate date = LocalDate.parse("2024-01-15");
		Page<Lesson> profLessons = lessonRepository.getByProfessorIdAndDate(id, date, PageRequest.of(0, 10));
		assertEquals(1, profLessons.getTotalElements());
		assertEquals("Math", profLessons.getContent().get(0).getSubject().getName());
	}

}

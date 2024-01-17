package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Grade;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Teacher;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		LessonRepository.class, TeacherRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class LessonRepositoryIT {

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	public void testSaveLesson() {

		Lesson lesson = new Lesson();
		Auditorium auditorium = new Auditorium(1L, "A101");
		Grade grade = new Grade(1L, "A");
		Subject subject = new Subject(1L, "Math", "Mathematics course");
		Teacher teacher = new Teacher();
		Role role = new Role(3L, "Teacher");
		teacher.setLogin("teacher1");
		teacher.setPassword("teacher123");
		teacher.setName("Teacher");
		teacher.setLastName("One");
		teacher.setRole(role);
		teacher.setGrade(null);
		lesson.setTeacher(teacher);
		lesson.setSubject(subject);
		lesson.setAuditorium(auditorium);
		lesson.setGrade(grade);
		Timestamp lessonTimestamp = Timestamp.valueOf("2024-01-15 09:00:00");
		lesson.setTime(lessonTimestamp);
		Teacher savedTeacher = teacherRepository.save(teacher);
		Lesson savedLesson = lessonRepository.save(lesson);
		assertNotNull(savedLesson.getId());

	}

	@Test
	public void testFindLessonById() {

		Long lessonId = 1L;
		Lesson lesson = lessonRepository.findById(lessonId).orElse(null);

		assertNotNull(lesson.getSubject());
		assertNotNull(lesson.getTeacher());
		assertNotNull(lesson.getAuditorium());
		assertNotNull(lesson.getGrade());

		assertEquals(1, lesson.getSubject().getId());
		assertEquals(4, lesson.getTeacher().getId());
		assertEquals("2024-01-15 09:00:00.0", lesson.getTime().toString());
		assertEquals(1, lesson.getAuditorium().getId());
		assertEquals(1, lesson.getGrade().getId());
	}

	@Test
	public void testUpdateLesson() {

		Long lessonId = 1L;
		Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
		assertNotNull(lesson);
		Lesson updatedLesson = lessonRepository.save(lesson);
		assertEquals(lessonId, updatedLesson.getId());

	}

	@Test
	public void testDeleteLesson() {

		Long lessonId = 1L;
		lessonRepository.deleteById(lessonId);
		assertNull(lessonRepository.findById(lessonId).orElse(null));

	}
}

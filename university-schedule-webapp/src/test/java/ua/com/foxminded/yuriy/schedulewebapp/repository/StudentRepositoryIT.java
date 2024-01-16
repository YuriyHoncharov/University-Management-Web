package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		StudentRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class StudentRepositoryIT {
	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testSaveStudent() {

		Student student = new Student();
		student.setLogin("newstudent");
		student.setPassword("password123");
		student.setName("New");
		student.setLastName("Student");
		student.setRole(new Role(2L, "Student"));
		Student savedStudent = studentRepository.save(student);
		assertNotNull(savedStudent.getId());
		assertEquals("newstudent", savedStudent.getLogin());
	}

	@Test
	public void testFindStudentById() {

		Long studentId = 2L;
		Student foundStudent = studentRepository.findById(studentId).orElse(null);
		assertNotNull(foundStudent);
		assertEquals("student1", foundStudent.getLogin());
	}

	@Test
	public void testUpdateStudent() {

		Long studentId = 2L;
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
		System.out.println("Retrieved Student: " + student);
		student.setName("UpdatedName");
		Student updatedStudent = studentRepository.save(student);
		assertEquals("UpdatedName", updatedStudent.getName());
	}

	@Test
	public void testDeleteStudent() {

		Long studentId = 2L;
		studentRepository.deleteById(studentId);
		assertNull(studentRepository.findById(studentId).orElse(null));
	}
}

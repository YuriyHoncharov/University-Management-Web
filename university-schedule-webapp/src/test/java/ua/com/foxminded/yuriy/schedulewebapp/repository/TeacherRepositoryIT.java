package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Teacher;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		TeacherRepository.class, RoleRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class TeacherRepositoryIT {

	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void testSaveTeacher() {

		Teacher teacher = new Teacher();
		teacher.setLogin("newTeacher");
		teacher.setPassword("password");
		teacher.setName("New");
		teacher.setLastName("Teacher");
		teacher.setRole(roleRepository.findById(3L).orElse(null));
		teacherRepository.save(teacher);
		assertNotNull(teacher.getId());
	}

	@Test
	public void testFindTeacherById() {

		Long teacherId = 4L;
		Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
		assertNotNull(teacher);
		assertEquals("Teacher One", teacher.getName());
	}

	@Test
	public void testUpdateTeacher() {

		Long teacherId = 4L;
		Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
		assertNotNull(teacher);
		teacher.setName("UpdatedName");
		teacherRepository.save(teacher);
		Teacher updatedTeacher = teacherRepository.findById(teacherId).orElse(null);
		assertNotNull(updatedTeacher);
		assertEquals("UpdatedName", updatedTeacher.getName());
	}

	@Test
	public void testDeleteTeacher() {

		Long teacherId = 4L;
		teacherRepository.deleteById(teacherId);
		assertFalse(teacherRepository.existsById(teacherId));
	}
}

package ua.com.foxminded.yuriy.schedulewebapp.repository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		ProfessorRepository.class, RoleRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class ProfessorRepositoryIT {

	@Autowired
	private ProfessorRepository teacherRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void should_Save_Professor() {

		Professor teacher = new Professor();
		teacher.setLogin("newProfessor");
		teacher.setPassword("password");
		teacher.setName("New");
		teacher.setLastName("Professor");
		teacher.setRole(roleRepository.findById(3L).orElse(null));
		teacherRepository.save(teacher);
		assertNotNull(teacher.getId());
	}

	@Test
	public void should_Find_Professor_By_Id() {

		Long teacherId = 4L;
		Professor teacher = teacherRepository.findById(teacherId).orElse(null);
		assertNotNull(teacher);
		assertEquals("Professor", teacher.getName());
	}

	@Test
	public void should_Update_Professor() {

		Long teacherId = 4L;
		Professor teacher = teacherRepository.findById(teacherId).orElse(null);
		assertNotNull(teacher);
		teacher.setName("UpdatedName");
		teacherRepository.save(teacher);
		Professor updatedProfessor = teacherRepository.findById(teacherId).orElse(null);
		assertNotNull(updatedProfessor);
		assertEquals("UpdatedName", updatedProfessor.getName());
	}

	@Test
	public void should_Delete_Professor() {

		Long teacherId = 4L;
		teacherRepository.deleteById(teacherId);
		assertNull(teacherRepository.findById(teacherId).orElse(null));
	}
}

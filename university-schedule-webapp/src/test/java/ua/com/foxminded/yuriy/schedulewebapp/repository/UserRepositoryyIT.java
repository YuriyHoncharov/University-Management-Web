package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.User;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { UserRepository.class,
		RoleRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class UserRepositoryyIT {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void testSaveUser() {

		User user = new User();
		user.setLogin("newUser");
		user.setPassword("password");
		user.setName("New");
		user.setLastName("User");
		user.setRole(roleRepository.findById(2L).orElse(null));
		userRepository.save(user);
		assertNotNull(user.getId());
	}

	@Test
	public void testFindUserById() {

		Long userId = 1L;
		User user = userRepository.findById(userId).orElse(null);
		assertNotNull(user);
		assertEquals("Admin", user.getName());
	}

	@Test
	public void testUpdateUser() {

		Long userId = 1L;
		User user = userRepository.findById(userId).orElse(null);
		assertNotNull(user);
		user.setName("UpdatedName");
		userRepository.save(user);
		User updatedUser = userRepository.findById(userId).orElse(null);
		assertNotNull(updatedUser);
		assertEquals("UpdatedName", updatedUser.getName());
	}

	@Test
	public void testDeleteUser() {

		Long userId = 1L;
		userRepository.deleteById(userId);
		assertFalse(userRepository.existsById(userId));
	}
}

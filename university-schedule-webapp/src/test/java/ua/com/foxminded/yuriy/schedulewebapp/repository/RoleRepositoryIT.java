package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		RoleRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class RoleRepositoryIT {
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void testSaveRole() {

		Role role = new Role();
		role.setName("NewRole");
		Role savedRole = roleRepository.save(role);
		assertNotNull(savedRole.getId());
		assertEquals("NewRole", savedRole.getName());
	}

	@Test
	public void testFindRoleById() {

		Long roleId = 1L;
		Optional<Role> optionalRole = roleRepository.findById(roleId);
		assertTrue(optionalRole.isPresent());
		Role role = optionalRole.get();
		assertEquals(roleId, role.getId());
	}

	@Test
	public void testFindAllRoles() {

		List<Role> roles = roleRepository.findAll();
		assertFalse(roles.isEmpty());
		assertEquals(3, roles.size());
	}

	@Test
	public void testUpdateRole() {

		Long roleId = 1L;
		Role role = roleRepository.findById(roleId).orElseThrow(null);
		role.setName("UpdatedRole");
		Role updatedRole = roleRepository.save(role);
		assertEquals(roleId, updatedRole.getId());
		assertEquals("UpdatedRole", updatedRole.getName());
	}

	@Test
	public void testDeleteRole() {

		Long roleId = 1L;
		roleRepository.deleteById(roleId);
		assertNull(roleRepository.findById(roleId).orElse(null));
	}
}

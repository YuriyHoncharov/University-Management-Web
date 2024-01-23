package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Admin;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		AdminRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class AdminRepositoryIT {

	@Autowired
	private AdminRepository adminRepository;

	@Test
	void should_Get_All_Admins() {
		List<Admin> admins = adminRepository.findAll();
		assertEquals(1, admins.size());
	}

	@Test
	void should_Get_Admin_By_Id() {
		Long adminId = 1L;
		Admin admin = adminRepository.findById(adminId).orElse(null);
		assertEquals("Admin", admin.getName());
	}
}

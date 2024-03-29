package ua.com.foxminded.yuriy.schedulewebapp.repository.integrationalTests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Headmaster;
import ua.com.foxminded.yuriy.schedulewebapp.repository.HeadmasterRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		HeadmasterRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class HeadmasterRepositoryIT {

	@Autowired
	private HeadmasterRepository headmasterRepository;

	@Test
	void should_Get_All_Headmasters() {
		List<Headmaster> headmasters = headmasterRepository.findAll();
		assertEquals(1, headmasters.size());
	}

	@Test
	void should_Get_Admin_By_Id() {
		Long headmasterId = 1L;
		Headmaster headmaster = headmasterRepository.findById(headmasterId).orElse(null);
		assertEquals("Headmaster", headmaster.getName());
	}
}

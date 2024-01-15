package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		AuditoriumRepository.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/clear-data.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class AuditoriumRepositoryIT {
	
	@Autowired
	private AuditoriumRepository auditoriumRepository;

	@Test
	void shouldGetAllAuditoriums() {
		
		List<Auditorium> auditoriums = auditoriumRepository.findAll();
		assertEquals(3, auditoriums.size());
	}

	@Test
	void shouldGetAuditoriumById() {
		
		Long auditoriumId = 1L;		
		Auditorium auditorium = auditoriumRepository.findById(auditoriumId).orElse(null);		
		assertEquals("A101", auditorium.getName());
	}
}

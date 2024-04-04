package ua.com.foxminded.yuriy.schedulewebapp.repository.controller.guestcontrollers;

import static org.mockito.Mockito.when;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ua.com.foxminded.yuriy.schedulewebapp.controllers.guestcontrollers.HousesController;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class HouseControllerTest {

	@Mock
	private HouseService houseService;

	@InjectMocks
	private HousesController housesController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(housesController).build();
	}

	@Test
	void shouldGet_houseDescription_Page() throws Exception {
		House house = new House();
		house.setId(1L);
		house.setHouse("Test House");
		when(houseService.getAll()).thenReturn(Collections.singletonList(house));

		mockMvc = MockMvcBuilders.standaloneSetup(housesController).build();
		mockMvc.perform(get("/houses")).andExpect(status().isOk()).andExpect(view().name("houses_info"))
				.andExpect(model().attributeExists("houses"))
				.andExpect(model().attribute("houses", Collections.singletonList(house)));
	}
}

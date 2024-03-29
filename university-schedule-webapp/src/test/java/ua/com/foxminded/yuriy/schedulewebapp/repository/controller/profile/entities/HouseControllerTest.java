package ua.com.foxminded.yuriy.schedulewebapp.repository.controller.profile.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;
import java.util.stream.IntStream;
import ua.com.foxminded.yuriy.schedulewebapp.controllers.profile.entities.HouseController;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;

@ExtendWith(MockitoExtension.class)
public class HouseControllerTest {

	@Mock
	private HouseService houseService;

	@InjectMocks
	private HouseController houseController;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(houseController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void shouldViewHousesPage() throws Exception {
		Page<House> pageHouses = Mockito.mock(Page.class);
		when(houseService.findAll(any())).thenReturn(pageHouses);
		mockMvc.perform(get("/profile/dashboard/houses")).andExpect(status().isOk())
				.andExpect(view().name("profile/entities/houses")).andExpect(model().attribute("houses", pageHouses))
				.andExpect(model().attribute("numbers", IntStream.range(1, pageHouses.getTotalPages()).toArray()));
		verify(houseService, times(1)).findAll(any());
	}

	@Test
	void shouldViewHouseEditPage() throws Exception {
		Long houseId = 1L;
		House house = new House(houseId, "HouseTest");
		when(houseService.getById(houseId)).thenReturn(Optional.of(house));
		mockMvc.perform(get("/profile/dashboard/houses/edit/{id}", houseId)).andExpect(status().isOk())
				.andExpect(view().name("profile/entities/edit/houseEdit")).andExpect(model().attribute("house", house));
		verify(houseService, times(1)).getById(houseId);
	}

	@Test
	void shouldViewHouseCreatePage() throws Exception {
		mockMvc.perform(get("/profile/dashboard/houses/create"))
				.andExpect(view().name("profile/entities/create/houseCreate"));
	}

	@Test
	void shouldDeleteHouse() throws Exception {
		Long houseId = 1L;
		mockMvc.perform(delete("/profile/dashboard/houses/delete/{id}", houseId)).andExpect(status().isOk())
				.andExpect(content().string("House deleted successfully"));
		verify(houseService, times(1)).delete(houseId);
	}

	@Test
	void shouldThrowExceptionOnDeleteRequest() throws Exception {
		Long houseId = 1L;
		when(houseService.delete(houseId)).thenThrow(new RuntimeException("error test info"));
		mockMvc.perform(delete("/profile/dashboard/houses/delete/{id}", houseId))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Failed to delete the house : " + "error test info"));
		verify(houseService, times(1)).delete(houseId);
	}

	@Test
	void shouldEditHouse() throws Exception {
		House house = new House(1L, "TestHouse");
		String houseJson = objectMapper.writeValueAsString(house);
		mockMvc.perform(put("/profile/dashboard/houses/update/{id}", house.getId())
				.contentType(MediaType.APPLICATION_JSON).content(houseJson)).andExpect(status().isOk());
		verify(houseService, times(1)).save(house);
	}

	@Test
	void shouldCreateHouse() throws Exception {
		House house = new House(1L, "TestHouse");
		String houseJsString = objectMapper.writeValueAsString(house);
		mockMvc.perform(
				post("/profile/dashboard/houses/create").contentType(MediaType.APPLICATION_JSON).content(houseJsString))
				.andExpect(status().isOk());
		verify(houseService, times(1)).save(house);
	}

	@Test
	void shouldNotCreateHouseIfAlreadyExist() throws Exception {
		House house = new House(1L, "TestHouse");
		String houseJsString = objectMapper.writeValueAsString(house);
		when(houseService.save(house)).thenThrow(new ValidationException("House with following name is already exist"));
		mockMvc
				.perform(post("/profile/dashboard/houses/create").contentType(MediaType.APPLICATION_JSON)
						.content(houseJsString))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("House with following name is already exist"));
		verify(houseService, times(1)).save(house);
	}

}

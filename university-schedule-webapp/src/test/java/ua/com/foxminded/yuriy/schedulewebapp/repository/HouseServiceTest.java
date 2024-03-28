package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.service.impl.HouseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {
	
	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private HouseServiceImpl houseServiceImpl;

	@Test
	public void should_Save_New_House_Correctly() {
		House house = new House(1L, "A");
		when(houseRepository.save(house)).thenReturn(house);
		House savedHouse = houseServiceImpl.save(house);
		assertNotNull(savedHouse);
		assertEquals("A", savedHouse.getHouse());
	}

	@Test
	public void testSave_ExistingHouse() {
		House house = new House(1L, "B");
		when(houseRepository.findByHouse(house.getHouse())).thenThrow(new ValidationException("Already Exist"));
		assertThrows(ValidationException.class, () -> houseServiceImpl.save(house));
	}
}

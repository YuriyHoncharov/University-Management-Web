package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.House;

public interface HouseService {
	
	List<House> getAll();

	Optional<House> getById(Long id);

	House save(House house);

	void delete(Long id);
}

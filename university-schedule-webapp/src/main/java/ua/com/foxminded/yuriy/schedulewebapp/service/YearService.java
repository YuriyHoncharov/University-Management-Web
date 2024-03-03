package ua.com.foxminded.yuriy.schedulewebapp.service;
import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;

public interface YearService {

	List<Year>getAll();
	
	Optional<Year> getById(Long id);
	
}

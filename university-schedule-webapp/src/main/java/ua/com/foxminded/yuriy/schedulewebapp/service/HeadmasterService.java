package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Headmaster;

public interface HeadmasterService {

	List<Headmaster> getAll();

	Optional<Headmaster> getById(Long id);

	Headmaster save(Headmaster headmaster);

	void delete(Long id);

}

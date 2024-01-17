package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;

public interface AuditoriumService {

	List<Auditorium> getAll();

	Optional<Auditorium> getById(Long id);

	Auditorium save(Auditorium auditorium);

	void delete(Long id);
}

package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;

public interface AuditoriumService {

	List<Auditorium> getAllAuditoriums();

	Optional<Auditorium> getAuditoriumById(Long id);

	Auditorium saveAuditorium(Auditorium auditorium);

	void deleteAuditorium(Long id);
}

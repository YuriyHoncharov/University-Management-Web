package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;

public interface SubjectService {
	
	List<Subject> getAll();

	Optional<Subject> getById(Long id);

	Subject save(Subject subject);

	void delete(Long id);
}

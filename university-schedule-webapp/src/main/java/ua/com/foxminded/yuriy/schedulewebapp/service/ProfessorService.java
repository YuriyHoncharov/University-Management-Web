package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;

public interface ProfessorService {
	
	List<Professor> getAll();

	Optional<Professor> getById(Long id);

	Professor save(Professor teacher);

	void delete(Long id);
}

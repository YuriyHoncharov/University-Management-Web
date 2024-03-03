package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;

public interface ProfessorService {

	List<ProfessorDto> getAll();

	Optional<Professor> getById(Long id);

	Professor save(Professor teacher);

	void delete(Long id);

}

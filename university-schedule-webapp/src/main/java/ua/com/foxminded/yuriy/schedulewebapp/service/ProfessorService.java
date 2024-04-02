package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;

public interface ProfessorService {

	Page<ProfessorDto> getAll(Pageable pageable);
	Optional<Professor> getById(Long id);
	Professor save(Professor teacher);
	Long delete(Long id);	
	List<ProfessorDto> getAll();	
	Professor professorBuilder(Professor professor, Long id);

}

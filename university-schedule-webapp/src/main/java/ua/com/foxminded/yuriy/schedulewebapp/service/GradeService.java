package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Grade;

public interface GradeService {
	
	List<Grade> getAll();

	Optional<Grade> getById(Long id);

	Grade save(Grade grade);

	void delete(Long id);
}

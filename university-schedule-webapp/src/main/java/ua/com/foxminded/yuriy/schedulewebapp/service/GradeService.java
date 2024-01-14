package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Grade;

public interface GradeService {
	
	List<Grade> getAllGrades();

	Optional<Grade> getGradeById(Long id);

	Grade saveGrade(Grade grade);

	void deleteGrade(Long id);
}

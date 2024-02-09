package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;

public interface LessonService {

	List<Lesson> getAll();

	Optional<Lesson> getById(Long id);

	Lesson save(Lesson lesson);

	void delete(Long id);

	List<Lesson> getByStudentIdAndFilters(Long studentId, LocalDateTime selectedDate);
	
	List<Lesson> getByProfessorIdAndDate(Long professorId, LocalDateTime selectedDate);
	
	List<Lesson> getByWizardIdAndDate(Long wizardId, String selectedDate);
	
	
}

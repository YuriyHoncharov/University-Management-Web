package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;

public interface LessonService {

	List<Lesson> getAll();

	Optional<Lesson> getById(Long id);

	Lesson save(Lesson lesson);

	void delete(Long id);

	List<Lesson> getByWizardIdAndFilters(Long wizardId, LocalDateTime selectedDate);
	
	List<Lesson> getByProfessorIdAndDate(Long professorId, LocalDateTime selectedDate);
}

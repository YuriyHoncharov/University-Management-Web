package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;

public interface LessonService {

	List<Lesson> getAll();

	Optional<Lesson> getById(Long id);

	Lesson save(Lesson lesson);

	void delete(Long id);

	List<LessonDto> getByStudentIdAndFilters(Long studentId, LocalDateTime selectedDate);
	
	List<LessonDto> getByProfessorIdAndDate(Long professorId, LocalDateTime selectedDate);
	
	List<LessonDto> getByWizardIdAndDate(Long wizardId, String selectedDate);
	
	Lesson update(Lesson lesson);
	
}

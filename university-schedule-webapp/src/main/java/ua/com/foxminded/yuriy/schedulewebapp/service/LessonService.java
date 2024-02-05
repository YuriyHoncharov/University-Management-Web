package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;

public interface LessonService {
	
	List<Lesson> getAll();

	Optional<Lesson> getById(Long id);

	Lesson save(Lesson lesson);

	void delete(Long id);
	
	List<Lesson> getByWizardId(Long wizardId);
	
	List<Lesson>getByWizardIdAndDayOfWeek(Long wizardId, int selectedDay);
	
	List<Lesson>getByWizardIdAndDate(Long wizardId, Timestamp selectedDate);
}

package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;

public interface LessonService {
	
	List<Lesson> getAllLessons();

	Optional<Lesson> getLessonById(Long id);

	Lesson saveLesson(Lesson lesson);

	void deleteLesson(Long id);
}

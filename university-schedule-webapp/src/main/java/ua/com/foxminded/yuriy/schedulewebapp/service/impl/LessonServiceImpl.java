package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.repository.LessonRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;

	@Autowired
	public LessonServiceImpl(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	@Override
	public List<Lesson> getAllLessons() {
		return lessonRepository.findAll();
	}

	@Override
	public Optional<Lesson> getLessonById(Long id) {
		return lessonRepository.findById(id);
	}

	@Override
	public Lesson saveLesson(Lesson lesson) {
		return lessonRepository.save(lesson);
	}

	@Override
	public void deleteLesson(Long id) {
		lessonRepository.deleteById(id);
	}
}

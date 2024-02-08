package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.repository.LessonRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;
	private final StudentRepository studentRepository;

	@Autowired
	public LessonServiceImpl(LessonRepository lessonRepository, StudentRepository studentRepository) {
		this.lessonRepository = lessonRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Lesson> getAll() {
		return lessonRepository.findAll();
	}

	@Override
	public Optional<Lesson> getById(Long id) {
		return lessonRepository.findById(id);
	}

	@Override
	public Lesson save(Lesson lesson) {
		return lessonRepository.save(lesson);
	}

	@Override
	public void delete(Long id) {
		lessonRepository.deleteById(id);
	}

	@Override
	public List<Lesson> getByWizardIdAndFilters(Long wizardId, LocalDateTime selectedDate) {
		Optional<Student> student = studentRepository.findById(wizardId);
		House houseId = student.get().getHouse();
		Year yearId = student.get().getYear();
		List<Subject> subjects = student.get().getSubjects();
		return lessonRepository.getByStudentIdAndDate(houseId, yearId, subjects, selectedDate);
	}

	@Override
	public List<Lesson> getByProfessorIdAndDate(Long professorId, LocalDateTime selectedDate) {

		return lessonRepository.getByProfessorIdAndDate(professorId, selectedDate);
	}
}

package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.LessonRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.ProfessorRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;

	@Autowired
	public LessonServiceImpl(LessonRepository lessonRepository, StudentRepository studentRepository,
			ProfessorRepository professorRepository) {
		this.lessonRepository = lessonRepository;
		this.studentRepository = studentRepository;
		this.professorRepository = professorRepository;
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
	public List<Lesson> getByStudentIdAndFilters(Long wizardId, LocalDateTime selectedDate) {

		Optional<Student> student = studentRepository.findById(wizardId);
		if (student.isPresent()) {
			House house = student.get().getHouse();
			Year year = student.get().getYear();
			List<Subject> subjects = student.get().getSubjects();
			return lessonRepository.getByStudentIdAndDate(house, year, subjects, selectedDate);
		} else {
			throw new UserNotFoundException("Any user was found with the followind ID : " + wizardId);
		}
	}

	@Override
	public List<Lesson> getByProfessorIdAndDate(Long professorId, LocalDateTime selectedDate) {

		if (professorRepository.findById(professorId).isPresent()) {
			return lessonRepository.getByProfessorIdAndDate(professorId, selectedDate);
		} else {
			throw new UserNotFoundException("Any user was found with the followind ID : " + professorId);
		}

	}

	@Override
	public List<Lesson> getByWizardIdAndDate(Long wizardId, String selectedDate) {

		LocalDateTime selectedDateStamp = parseSelectedDate(selectedDate);
		if (studentRepository.findById(wizardId).isPresent()) {
			return getByStudentIdAndFilters(wizardId, selectedDateStamp);
		} else {
			return getByProfessorIdAndDate(wizardId, selectedDateStamp);
		}

	}

	private LocalDateTime parseSelectedDate(String selectedDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (selectedDate == null || selectedDate.isEmpty()) {
			return LocalDateTime.now();
		} else {
			return LocalDate.parse(selectedDate, formatter).atStartOfDay();
		}
	}
}

package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.LessonRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.ProfessorRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;

@Service
@RequiredArgsConstructor

public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;

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
	public Page<LessonDto> getByStudentIdAndDate(Long wizardId, LocalDate selectedDate, Pageable pageable) {

		Optional<Student> student = studentRepository.findById(wizardId);
		if (student.isPresent()) {
			House house = student.get().getHouse();
			Year year = student.get().getYear();
			List<Subject> subjects = student.get().getSubjects();
			return lessonRepository.getByStudentIdAndDate(house, year, subjects, selectedDate, pageable).map(LessonDto::new);
					
		} else {
			throw new UserNotFoundException("Any user was found with the followind ID : " + wizardId);
		}
	}

	@Override
	public Page<LessonDto> getByProfessorIdAndDate(Long professorId, LocalDate selectedDate, Pageable pageable) {

		if (professorRepository.findById(professorId).isPresent()) {
			return lessonRepository.getByProfessorIdAndDate(professorId, selectedDate, pageable).map(LessonDto::new);
		} else {
			throw new UserNotFoundException("Any user was found with the followind ID : " + professorId);
		}

	}

	@Override
	public Page<LessonDto> getByWizardIdAndDate(Long wizardId, String selectedDate, Pageable pageable) {

		LocalDate selectedDateStamp = parseSelectedDate(selectedDate);
		if (studentRepository.findById(wizardId).isPresent()) {
			return getByStudentIdAndDate(wizardId, selectedDateStamp, pageable);
		} else {
			return getByProfessorIdAndDate(wizardId, selectedDateStamp, pageable);
		}

	}

	private LocalDate parseSelectedDate(String selectedDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (selectedDate == null || selectedDate.isEmpty()) {
			return LocalDate.now();
		} else {
			return LocalDate.parse(selectedDate, formatter);
		}
	}

	@Override
	public List<LessonDto> getAllLessonsDto() {
		return lessonRepository.findAll().stream().map(LessonDto::new).collect(Collectors.toList());
	}

	@Override
	public Page<LessonDto> getAllByPage(Pageable pageable) {
		Page<Lesson>pageLesson = lessonRepository.findAll(pageable);
		return pageLesson.map(LessonDto::new);
	}

	@Override
	public Page<LessonDto> getAllByDate(String selectedDate, Pageable pageable) {
		LocalDate selectedDateStamp = parseSelectedDate(selectedDate);
		Page<Lesson>pageLesson = lessonRepository.getByDate(selectedDateStamp, pageable);
		return pageLesson.map(LessonDto::new);
	}

	@Override
	public Page<LessonDto> getByStudentId(Long wizardId, Pageable pageable) {
		Optional<Student> student = studentRepository.findById(wizardId);
		if (student.isPresent()) {
			House house = student.get().getHouse();
			Year year = student.get().getYear();
			List<Subject> subjects = student.get().getSubjects();
			return lessonRepository.getByStudentId(house, year, subjects, pageable).map(LessonDto::new);
					
		} else {
			throw new UserNotFoundException("Any user was found with the followind ID : " + wizardId);
		}
	}

	@Override
	public Page<LessonDto> getByProfessorId(Long wizardId, Pageable pageable) {
		if (professorRepository.findById(wizardId).isPresent()) {
			return lessonRepository.getByProfessorId(wizardId, pageable).map(LessonDto::new);
		} else {
			throw new UserNotFoundException("Any user was found with the followind ID : " + wizardId);
		}
	}

	@Override
	public Page<LessonDto> getByWizardId(Long wizardId, Pageable pageable) {
		if (studentRepository.findById(wizardId).isPresent()) {
			return getByStudentId(wizardId, pageable);
		} else {
			return getByProfessorId(wizardId, pageable);
		}
	}

}

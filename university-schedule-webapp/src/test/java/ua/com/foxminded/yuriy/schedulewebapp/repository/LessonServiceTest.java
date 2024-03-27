package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.service.impl.LessonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

	@Mock
	private LessonRepository lessonRepository;

	@Mock
	private ProfessorRepository professorRepository;

	@Mock
	private SubjectRepository subjectRepository;

	@InjectMocks
	private LessonServiceImpl lessonServiceImpl;

	@Test
	public void shouldSaveLessonSuccessfullyWhenProperTeacherAndNoConflict() {

		// LESSON CREATION
		Long id = 1L;
		Subject subject = new Subject(1L, "test", "test");
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		Professor professor = new Professor();
		professor.setId(id);
		professor.setLastName("test");
		professor.setLogin("test");
		professor.setName("test");
		professor.setPassword("test");
		professor.setRole(new Role(1L, "test"));
		professor.setSubjects(subjects);
		LocalDate date = LocalDate.parse("2024-03-21");
		LocalTime time = LocalTime.parse("10:00:00");
		LocalTime endTime = LocalTime.parse("10:45:00");
		Auditorium auditorium = new Auditorium(1L, "test");
		House house = new House();
		house.setHouse("test");
		house.setId(1L);
		Year year = new Year();
		year.setId(1L);
		year.setYearValue(1);
		Lesson lesson = new Lesson(id, subject, professor, date, time, endTime, auditorium, house, year);
		Lesson savedLesson = new Lesson(id, subject, professor, date, time, endTime, auditorium, house, year);

		when(professorRepository.findById(lesson.getProfessor().getId())).thenReturn(Optional.of(professor));
		when(subjectRepository.findById(lesson.getSubject().getId())).thenReturn(Optional.of(subject));
		when(professorRepository.getBySubject(subjects)).thenReturn(professor);
		when(lessonRepository.findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59), id))
				.thenReturn(Collections.emptyList());
		when(lessonRepository.save(lesson)).thenReturn(savedLesson);
		Lesson result = lessonServiceImpl.save(lesson);
		verify(professorRepository).findById(lesson.getProfessor().getId());
		verify(subjectRepository).findById(lesson.getSubject().getId());
		verify(professorRepository).getBySubject(subjects);
		verify(lessonRepository).findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59),
				id);
		verify(lessonRepository).save(lesson);
		assertEquals(lesson, result);
	}

	@Test
	public void shouldThrowValidationExceptionWhenSchedulingConflict() {

		Long id = 1L;
		Subject subject = new Subject(1L, "test", "test");
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		Professor professor = new Professor();
		professor.setId(id);
		professor.setLastName("test");
		professor.setLogin("test");
		professor.setName("test");
		professor.setPassword("test");
		professor.setRole(new Role(1L, "test"));
		professor.setSubjects(subjects);
		LocalDate date = LocalDate.parse("2024-03-21");
		LocalTime time = LocalTime.parse("10:00:00");
		LocalTime endTime = LocalTime.parse("10:45:00");
		Auditorium auditorium = new Auditorium(1L, "test");
		House house = new House();
		house.setHouse("test");
		house.setId(1L);
		Year year = new Year();
		year.setId(1L);
		year.setYearValue(1);
		Lesson lesson = new Lesson(id, subject, professor, date, time, endTime, auditorium, house, year);
		List<Lesson> conflictingLesson = new ArrayList<>();
		conflictingLesson.add(lesson);

		when(professorRepository.findById(lesson.getProfessor().getId())).thenReturn(Optional.of(professor));
		when(subjectRepository.findById(lesson.getSubject().getId())).thenReturn(Optional.of(subject));
		when(professorRepository.getBySubject(subjects)).thenReturn(professor);
		when(lessonRepository.findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59), id))
				.thenReturn(conflictingLesson);

		assertThrows(ValidationException.class, () -> lessonServiceImpl.save(lesson));

		verify(professorRepository).findById(lesson.getProfessor().getId());
		verify(subjectRepository).findById(lesson.getSubject().getId());
		verify(professorRepository).getBySubject(subjects);
		verify(lessonRepository).findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59),
				id);
	}

	@Test
	public void shouldThrowValidationExceptionWhenImproperTeacher() {
		// LESSON CREATION
		Long id = 1L;
		Subject subject = new Subject(1L, "test", "test");
		Subject subject2 = new Subject(2L, "test1", "test1");

		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);

		List<Subject> anotherSubject = new ArrayList<>();
		anotherSubject.add(subject2);

		Professor professor = new Professor();
		professor.setId(id);
		professor.setLastName("test");
		professor.setLogin("test");
		professor.setName("test");
		professor.setPassword("test");
		professor.setRole(new Role(1L, "test"));
		professor.setSubjects(subjects);

		Professor anotherProfessor = new Professor();
		anotherProfessor.setSubjects(anotherSubject);

		LocalDate date = LocalDate.parse("2024-03-21");
		LocalTime time = LocalTime.parse("10:00:00");
		LocalTime endTime = LocalTime.parse("10:45:00");
		Auditorium auditorium = new Auditorium(1L, "test");
		House house = new House();
		house.setHouse("test");
		house.setId(1L);
		Year year = new Year();
		year.setId(1L);
		year.setYearValue(1);
		Lesson lesson = new Lesson(id, subject, professor, date, time, endTime, auditorium, house, year);

		when(professorRepository.findById(lesson.getProfessor().getId())).thenReturn(Optional.of(professor));
		when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));
		when(professorRepository.getBySubject(subjects)).thenReturn(anotherProfessor);

		assertThrows(ValidationException.class, () -> lessonServiceImpl.save(lesson));

		verify(professorRepository).findById(lesson.getProfessor().getId());
		verify(subjectRepository).findById(id);
		verify(professorRepository).getBySubject(subjects);
	}
}

package ua.com.foxminded.yuriy.schedulewebapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
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
import ua.com.foxminded.yuriy.schedulewebapp.service.AuditoriumService;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.YearService;
import ua.com.foxminded.yuriy.schedulewebapp.service.impl.LessonServiceImpl;
import ua.com.foxminded.yuriy.schedulewebapp.service.impl.SubjectServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

	@Mock
	private LessonRepository lessonRepository;

	@Mock
	private ProfessorRepository professorRepository;

	@Mock
	private SubjectRepository subjectRepository;
	
	@Mock
	private SubjectService subjectService;
	
	@Mock
	private ProfessorService professorService;
	
	@Mock
	private AuditoriumService auditoriumService;
	
	@Mock
	private HouseService houseService;
	
	@Mock
	private YearService yearService;
	
	@InjectMocks
	private LessonServiceImpl lessonServiceImpl;

	@Test
	public void shouldSaveLessonSuccessfullyWhenProperTeacherAndNoConflict() {
		// PROFESSOR
		Long id = 1L;
		Professor professor = new Professor();
		professor.setId(id);
		professor.setLastName("test");
		professor.setLogin("test");
		professor.setName("test");
		professor.setPassword("test");
		professor.setRole(new Role(1L, "test"));
		// SUBJECTS
		Subject subject = new Subject(1L, "test", "test", professor);
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		// SUBJECTS ASSIGNEMENT
		professor.setSubject(subject);
		// DATA AND TIME
		LocalDate date = LocalDate.parse("2024-03-21");
		LocalTime time = LocalTime.parse("10:00:00");
		LocalTime endTime = LocalTime.parse("10:45:00");
		// AUDITORIUM
		Auditorium auditorium = new Auditorium(1L, "test");
		// HOUSE
		House house = new House();
		house.setHouse("test");
		house.setId(1L);
		// YEAR
		Year year = new Year();
		year.setId(1L);
		year.setYearValue(1);
		// LESSONS
		Lesson lesson = new Lesson(id, subject, professor, date, time, endTime, auditorium, house, year);
		Lesson savedLesson = new Lesson(id, subject, professor, date, time, endTime, auditorium, house, year);
		when(professorRepository.findById(lesson.getProfessor().getId())).thenReturn(Optional.of(professor));
		when(subjectRepository.findById(lesson.getSubject().getId())).thenReturn(Optional.of(subject));
		when(lessonRepository.findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59), id))
				.thenReturn(Collections.emptyList());
		when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));
		when(lessonRepository.save(lesson)).thenReturn(savedLesson);
		when(subjectService.getById(subject.getId())).thenReturn(Optional.of(subject));
		when(professorService.getById(professor.getId())).thenReturn(Optional.of(professor));
		when(auditoriumService.getById(auditorium.getId())).thenReturn(Optional.of(auditorium));
		when(houseService.getById(house.getId())).thenReturn(Optional.of(house));
		when(yearService.getById(year.getId())).thenReturn(Optional.of(year));
		Lesson result = lessonServiceImpl.save(lesson);
		verify(professorRepository).findById(lesson.getProfessor().getId());
		verify(subjectRepository).findById(lesson.getSubject().getId());
		verify(lessonRepository).findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59),
				id);
		verify(lessonRepository).save(lesson);
		assertEquals(lesson, result);
	}

	@Test
	public void shouldThrowValidationExceptionWhenSchedulingConflict() {

		// PROFESSOR
		Long id = 1L;
		Professor professor = new Professor();
		professor.setId(id);
		professor.setLastName("test");
		professor.setLogin("test");
		professor.setName("test");
		professor.setPassword("test");
		professor.setRole(new Role(1L, "test"));
		// SUBJECT
		Subject subject = new Subject(1L, "test", "test", professor);
		// SUBJECT ASSIGNEMENT
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		professor.setSubject(subject);
		// DATA AND TIME
		LocalDate date = LocalDate.parse("2024-03-21");
		LocalTime time = LocalTime.parse("10:00:00");
		LocalTime endTime = LocalTime.parse("10:45:00");
		Auditorium auditorium = new Auditorium(1L, "test");
		// HOUSE
		House house = new House();
		house.setHouse("test");
		house.setId(1L);
		// YEAR
		Year year = new Year();
		year.setId(1L);
		year.setYearValue(1);
		// LESSON
		Lesson lesson = new Lesson(id, subject, professor, date, time, endTime, auditorium, house, year);
		List<Lesson> conflictingLesson = new ArrayList<>();
		conflictingLesson.add(lesson);

		when(professorRepository.findById(lesson.getProfessor().getId())).thenReturn(Optional.of(professor));
		when(lessonRepository.findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59), id))
				.thenReturn(conflictingLesson);
		when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));
		when(subjectRepository.findById(lesson.getSubject().getId())).thenReturn(Optional.of(subject));
		when(subjectService.getById(subject.getId())).thenReturn(Optional.of(subject));
		when(professorService.getById(professor.getId())).thenReturn(Optional.of(professor));
		when(auditoriumService.getById(auditorium.getId())).thenReturn(Optional.of(auditorium));
		when(houseService.getById(house.getId())).thenReturn(Optional.of(house));
		when(yearService.getById(year.getId())).thenReturn(Optional.of(year));
		assertThrows(ValidationException.class, () -> lessonServiceImpl.save(lesson));
		verify(professorRepository).findById(lesson.getProfessor().getId());
		verify(subjectRepository).findById(lesson.getSubject().getId());
		verify(lessonRepository).findConflictingLessons(auditorium, date, time.minusMinutes(14), time.plusMinutes(59),
				id);
	}

	@Test
	public void shouldThrowValidationExceptionWhenImproperTeacher() {
		// PROFESSOR NUMBER 1
		Long id = 1L;
		Professor professor = new Professor();
		professor.setId(id);
		professor.setLastName("test");
		professor.setLogin("test");
		professor.setName("test");
		professor.setPassword("test");
		professor.setRole(new Role(1L, "test"));
		// PROFESSOR NUMBER 2 (IMPRORPER)
		Professor anotherProfessor = new Professor();
		// LESSON CREATION
		Subject subject = new Subject(1L, "test", "test", anotherProfessor);
		Subject subject2 = new Subject(2L, "test1", "test1", professor);
		// SUBJECT
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		List<Subject> anotherSubject = new ArrayList<>();
		anotherSubject.add(subject2);
		// SUBJECT ASSIGNEMENT
		professor.setSubject(subject2);
		anotherProfessor.setSubject(subject);
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
		Lesson lesson = new Lesson(id, subject2, professor, date, time, endTime, auditorium, house, year);
		when(subjectRepository.findById(lesson.getSubject().getId())).thenReturn(Optional.of(subject2));
		when(professorRepository.findById(lesson.getProfessor().getId())).thenReturn(Optional.of(anotherProfessor));
		when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));
		when(subjectService.getById(subject2.getId())).thenReturn(Optional.of(subject2));
		when(professorService.getById(professor.getId())).thenReturn(Optional.of(professor));
		when(auditoriumService.getById(auditorium.getId())).thenReturn(Optional.of(auditorium));
		when(houseService.getById(house.getId())).thenReturn(Optional.of(house));
		when(yearService.getById(year.getId())).thenReturn(Optional.of(year));
		assertThrows(ValidationException.class, () -> lessonServiceImpl.save(lesson));
		verify(professorRepository, times(1)).findById(lesson.getProfessor().getId());
		verify(subjectRepository, times(1)).findById(lesson.getSubject().getId());
	}
}

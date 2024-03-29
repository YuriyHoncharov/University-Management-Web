package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.LessonRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.ProfessorRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.SubjectRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.WizardRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.AuditoriumService;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.LessonService;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.YearService;

@Service
@RequiredArgsConstructor

public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;
	private final SubjectRepository subjectRepository;
	private final WizardRepository wizardRepository;
	private final SubjectService subjectService;
	private final ProfessorService professorService;
	private final AuditoriumService auditoriumService;
	private final YearService yearService;
	private final HouseService houseService;

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

		if (properTeacher(lesson)) {
			if (!hasScheduleConflict(lesson)) {
				return lessonRepository.save(lesson);
			} else {
				throw new ValidationException("Another Lesson is already assigned to this Auditorium for "
						+ lesson.getDate() + " and time " + lesson.getTime());
			}
		} else {
			throw new ValidationException(lesson.getProfessor().getName() + " " + lesson.getProfessor().getLastName()
					+ " don't teaching : " + lesson.getSubject().getName());
		}
	}

	@Override
	public void delete(Long id) {
		lessonRepository.deleteById(id);
	}

	private boolean hasScheduleConflict(Lesson lesson) {

		Auditorium auditorium = lesson.getAuditorium();
		LocalDate date = lesson.getDate();
		LocalTime startTime = lesson.getTime().minusMinutes(14);
		LocalTime endTime = lesson.getTime().plusMinutes(59);
		Long lessonId = lesson.getId();
		return !lessonRepository.findConflictingLessons(auditorium, date, startTime, endTime, lessonId).isEmpty();

	}

	private boolean properTeacher(Lesson lesson) {

		Subject subject = subjectRepository.findById(lesson.getSubject().getId()).get();
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		Professor prof = professorRepository.findById(lesson.getProfessor().getId()).get();
		if (prof.equals(subject.getProfessor())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<LessonDto> getByStudentIdAndDate(Long wizardId, LocalDate selectedDate, Pageable pageable) {

		Student student = studentRepository.findById(wizardId)
				.orElseThrow(() -> new UserNotFoundException("Any user was found with the followind ID : " + wizardId));
		House house = student.getHouse();
		Year year = student.getYear();
		List<Subject> subjects = student.getSubjects();
		return lessonRepository.getByStudentIdAndDate(house, year, subjects, selectedDate, pageable).map(LessonDto::new);

	}

	@Override
	public Page<LessonDto> getByProfessorIdAndDate(Long professorId, LocalDate selectedDate, Pageable pageable) {

		professorRepository.findById(professorId)
				.orElseThrow(() -> new UserNotFoundException("Any user was found with the followind ID : " + professorId));
		return lessonRepository.getByProfessorIdAndDate(professorId, selectedDate, pageable).map(LessonDto::new);

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
		Page<Lesson> pageLesson = lessonRepository.findAll(pageable);
		return pageLesson.map(LessonDto::new);
	}

	@Override
	public Page<LessonDto> getAllByDate(String selectedDate, Pageable pageable) {
		LocalDate selectedDateStamp = parseSelectedDate(selectedDate);
		Page<Lesson> pageLesson = lessonRepository.getByDate(selectedDateStamp, pageable);
		return pageLesson.map(LessonDto::new);
	}

	@Override
	public Page<LessonDto> getByStudentId(Long wizardId, Pageable pageable) {
		Student student = studentRepository.findById(wizardId)
				.orElseThrow(() -> new UserNotFoundException("Any student was found with the followind ID : " + wizardId));
		House house = student.getHouse();
		Year year = student.getYear();
		List<Subject> subjects = student.getSubjects();
		return lessonRepository.getByStudentId(house, year, subjects, pageable).map(LessonDto::new);

	}

	@Override
	public Page<LessonDto> getByProfessorId(Long wizardId, Pageable pageable) {
		if (professorRepository.findById(wizardId).isPresent()) {
			return lessonRepository.getByProfessorId(wizardId, pageable).map(LessonDto::new);
		} else {
			throw new UserNotFoundException("Any Professor was found with the followind ID : " + wizardId);
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

	@Override
	public Page<LessonDto> getLessonsByFilters(String login, String selectedDate, Authentication authentication,
			Integer page) {

		Page<LessonDto> pageLessons = Page.empty();
		boolean isAdmin = (login).equals("[ROLE_HEADMASTER]");
		boolean isStudent = (login).equals("[ROLE_STUDENT]");
		boolean isProfessor = (login).equals("[ROLE_PROFESSOR]");

		// ADMIN
		if (isAdmin) {
			if (selectedDate != null) {
				pageLessons = getAllByDate(selectedDate, PageRequest.of(page, 7));

			} else {
				pageLessons = getAllByPage(PageRequest.of(page, 7));
			}
		}

		else if (isStudent || isProfessor) {
			String name = authentication.getName();
			Long wizardId = wizardRepository.findByLogin(name).get().getId();
			if (selectedDate != null) {
				pageLessons = getByWizardIdAndDate(wizardId, selectedDate, PageRequest.of(page, 7));

			} else {
				pageLessons = getByWizardId(wizardId, PageRequest.of(page, 7));
			}
		}

		return pageLessons;
	}

	@Override
	public Lesson lessonBuilder(Lesson lesson, Long id) {
		Lesson existingLesson = new Lesson();
		if (id != null) {
			existingLesson = getById(id).get();
		}
			Subject subject = subjectService.getById(lesson.getSubject().getId()).get();
			Professor professor = professorService.getById(lesson.getProfessor().getId()).get();
			Auditorium auditorium = auditoriumService.getById(lesson.getAuditorium().getId()).get();
			House house = houseService.getById(lesson.getHouse().getId()).get();
			Year year = yearService.getById(lesson.getYear().getId()).get();

			existingLesson.setSubject(subject);
			existingLesson.setProfessor(professor);
			existingLesson.setDate(lesson.getDate());
			existingLesson.setTime(lesson.getTime());
			existingLesson.setEndTime(lesson.getEndTime());
			existingLesson.setAuditorium(auditorium);
			existingLesson.setHouse(house);
			existingLesson.setYear(year);
		
		return existingLesson;
	}

}

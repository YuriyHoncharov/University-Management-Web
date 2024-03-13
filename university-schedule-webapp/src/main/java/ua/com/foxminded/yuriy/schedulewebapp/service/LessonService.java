package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.LessonDto;

public interface LessonService {

	List<Lesson> getAll();

	Optional<Lesson> getById(Long id);

	Lesson save(Lesson lesson);

	void delete(Long id);

	List<LessonDto> getAllLessonsDto();

	Page<LessonDto> getByStudentIdAndDate(Long studentId, LocalDate selectedDate, Pageable pageable);

	Page<LessonDto> getByProfessorIdAndDate(Long professorId, LocalDate selectedDate, Pageable pageable);

	Page<LessonDto> getByWizardIdAndDate(Long wizardId, String selectedDate, Pageable pageable);

	Page<LessonDto> getAllByPage(Pageable pageable);

	Page<LessonDto> getAllByDate(String selectedDate, Pageable pageable);
	
	Page<LessonDto> getByStudentId(Long wizardId, Pageable pageable);
	
	Page<LessonDto> getByProfessorId(Long wizardId, Pageable pageable);
	
	Page<LessonDto> getByWizardId(Long wizardId, Pageable pageable);

}

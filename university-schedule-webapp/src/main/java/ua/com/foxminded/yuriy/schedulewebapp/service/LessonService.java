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

	List<LessonDto> getByStudentIdAndFilters(Long studentId, LocalDate selectedDate);
	
	List<LessonDto> getByProfessorIdAndDate(Long professorId, LocalDate selectedDate);
	
	List<LessonDto> getByWizardIdAndDate(Long wizardId, String selectedDate);
	
	Page<LessonDto> getAllByPage(Pageable pageable);
	
	Page<LessonDto> getAllByDate(String selectedDate, Pageable pageable);
			
}

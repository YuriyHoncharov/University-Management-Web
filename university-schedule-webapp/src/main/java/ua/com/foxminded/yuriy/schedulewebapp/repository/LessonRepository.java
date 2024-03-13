package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

	@Query("SELECT DISTINCT l FROM Lesson l WHERE l.house =:house AND l.year =:year AND l.subject IN :subjects AND l.date = :selectedDate")
	Page<Lesson> getByStudentIdAndDate(House house, Year year, List<Subject> subjects, LocalDate selectedDate, Pageable pageable);

	@Query("SELECT DISTINCT l FROM Lesson l WHERE l.professor.id =:professorId AND l.date = :selectedDate")
	Page<Lesson> getByProfessorIdAndDate(Long professorId, LocalDate selectedDate, Pageable pageable);

	@Query("SELECT DISTINCT l FROM Lesson l WHERE l.date = :selectedDate")
	Page<Lesson> getByDate(LocalDate selectedDate, Pageable pageable);

	Page<Lesson> findAll(Pageable pageable);
	
	@Query("SELECT DISTINCT l FROM Lesson l WHERE l.house =:house AND l.year =:year AND l.subject IN :subjects")
	Page<Lesson> getByStudentId(House house, Year year, List<Subject> subjects, Pageable pageable);

	@Query("SELECT DISTINCT l FROM Lesson l WHERE l.professor.id =:professorId")
	Page<Lesson> getByProfessorId(Long professorId, Pageable pageable);

}

package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

	@Query("SELECT DISTINCT l FROM Lesson l WHERE l.house =:house AND l.year =:year AND l.subject IN :subjects AND DATE(l.time) = DATE(:selectedDate)")
	List<Lesson> getByStudentIdAndDate(House house, Year year, List<Subject>subjects, LocalDateTime selectedDate);

	@Query("SELECT DISTINCT l FROM Lesson l WHERE l.professor.id =:professorId AND DATE(l.time) = DATE(:selectedDate)")
	List<Lesson> getByProfessorIdAndDate(Long professorId, LocalDateTime selectedDate);
	
	Lesson update (Lesson lesson);
}

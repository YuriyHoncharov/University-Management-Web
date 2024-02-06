package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

	@Query("SELECT DISTINCT l FROM Lesson l JOIN Student s ON :wizardId = s.id"
			+ " WHERE l.subject = ANY elements(s.subjects)" + " AND l.house = s.house"
			+ " AND (:selectedDay IS NULL OR extract(dow from l.time) = :selectedDay)"
			+ " AND (:selectedDate IS NULL OR DATE(l.time) = DATE(:selectedDate))" + " AND l.year = s.year")
	List<Lesson> getByStudentIdAndFilters(@Param("wizardId") Long wizardId, @Param("selectedDay") Integer selectedDay,
			@Param("selectedDate") Timestamp selectedDate);

	@Query("SELECT DISTINCT l FROM Lesson l JOIN Professor p ON :wizardId = p.id"
			+ " WHERE l.subject = ANY elements(p.subjects)"
			+ " AND (:selectedDay IS NULL OR extract(dow from l.time) =:selectedDay)"
			+ " AND (:selectedDate IS NULL OR DATE(l.time) = DATE(:selectedDate))")
	List<Lesson> getByProfessorIdAndDateOrDayWeek(@Param("wizardId") Long wizardId,
			@Param("selectedDay") Integer selectedDay, @Param("selectedDate") Timestamp selectedDate);

}

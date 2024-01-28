package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
	@Query("SELECT DISTINCT l From Lesson l JOIN Student s ON l.house.id = s.house.id JOIN s.subjects subj WHERE s.id =:wizardId")
	
	List<Lesson> getByWizardId(Long wizardId);
}

package ua.com.foxminded.yuriy.schedulewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}

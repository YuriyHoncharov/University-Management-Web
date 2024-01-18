package ua.com.foxminded.yuriy.schedulewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}

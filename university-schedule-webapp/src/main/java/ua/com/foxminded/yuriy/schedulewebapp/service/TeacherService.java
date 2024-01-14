package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Teacher;

public interface TeacherService {
	
	List<Teacher> getAllTeachers();

	Optional<Teacher> getTeacherById(Long id);

	Teacher saveTeacher(Teacher teacher);

	void deleteTeacher(Long id);
}

package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Teacher;

public interface TeacherService {
	
	List<Teacher> getAll();

	Optional<Teacher> getById(Long id);

	Teacher save(Teacher teacher);

	void delete(Long id);
}

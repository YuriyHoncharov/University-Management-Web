package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;

public interface StudentService {
	
	List<Student> getAll();	

	Optional<Student> getById(Long id);

	Student save(Student student);

	void delete(Long id);
}

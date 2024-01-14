package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Optional<Student> getStudentById(Long id);

	Student saveStudent(Student student);

	void deleteStudent(Long id);
}

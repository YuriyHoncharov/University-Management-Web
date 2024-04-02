package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.StudentDto;

public interface StudentService {

	List<StudentDto> getAll();
	Optional<Student> getById(Long id);
	Student save(Student student);
	Long delete(Long id);
	Page<StudentDto> findAll(Pageable pageable);	
	Student studentBuilder(Student student, Long id);	
	Student editStudentSubjects(Long studentId, Long subjectId);
}

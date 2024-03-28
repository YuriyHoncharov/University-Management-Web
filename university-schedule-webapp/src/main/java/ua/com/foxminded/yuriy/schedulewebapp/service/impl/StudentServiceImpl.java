package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.StudentService;

@Service
@RequiredArgsConstructor

public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Override
	public List<StudentDto> getAll() {
		return studentRepository.findAll().stream().map(StudentDto::new).collect(Collectors.toList());
	}

	@Override
	public Optional<Student> getById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	@Transactional
	public Long delete(Long id) {
		String errorMessage = String.format("Student with following ID : %d is not present in DataBase", id);
		studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(errorMessage));
		studentRepository.deleteById(id);
		return id;
	}

	@Override
	public Page<StudentDto> findAll(Pageable pageable) {
		Page<Student> pageStudent = studentRepository.findAll(pageable);
		return pageStudent.map(StudentDto::new);
	}
}

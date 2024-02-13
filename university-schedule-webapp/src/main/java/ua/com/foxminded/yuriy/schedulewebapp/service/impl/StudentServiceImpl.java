package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.repository.AuditoriumRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.StudentService;

@Service
@RequiredArgsConstructor

public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Override
	public List<Student> getAll() {
		return studentRepository.findAll();
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
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}
}

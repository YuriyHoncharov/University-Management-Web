package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Teacher;
import ua.com.foxminded.yuriy.schedulewebapp.repository.TeacherRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final TeacherRepository teacherRepository;

	@Autowired
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<Teacher> getAll() {
		return teacherRepository.findAll();
	}

	@Override
	public Optional<Teacher> getById(Long id) {
		return teacherRepository.findById(id);
	}

	@Override
	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public void delete(Long id) {
		teacherRepository.deleteById(id);
	}
}

package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;
import ua.com.foxminded.yuriy.schedulewebapp.repository.AuditoriumRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.ProfessorRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;

@Service
@RequiredArgsConstructor

public class ProfessorServiceImpl implements ProfessorService {

	private final ProfessorRepository teacherRepository;

	@Override
	public List<ProfessorDto> getAll() {
		return teacherRepository.findAll().stream().map(ProfessorDto::new).collect(Collectors.toList());
	}

	@Override
	public Optional<Professor> getById(Long id) {
		return teacherRepository.findById(id);
	}

	@Override
	public Professor save(Professor teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public void delete(Long id) {
		teacherRepository.deleteById(id);
	}
}

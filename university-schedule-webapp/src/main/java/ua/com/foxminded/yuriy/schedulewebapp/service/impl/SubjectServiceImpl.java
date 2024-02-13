package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.repository.AuditoriumRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.SubjectRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;

@Service
@RequiredArgsConstructor

public class SubjectServiceImpl implements SubjectService {

	private final SubjectRepository subjectRepository;

	@Override
	public List<Subject> getAll() {
		return subjectRepository.findAll();
	}

	@Override
	public Optional<Subject> getById(Long id) {
		return subjectRepository.findById(id);
	}

	@Override
	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public void delete(Long id) {
		subjectRepository.deleteById(id);
	}
}

package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.repository.SubjectRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	private final SubjectRepository subjectRepository;

	@Autowired
	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

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

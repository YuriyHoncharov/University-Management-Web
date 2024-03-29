package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.ProfessorDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.SubjectNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.ProfessorRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.ProfessorService;
import ua.com.foxminded.yuriy.schedulewebapp.service.RoleService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;

@Service
@RequiredArgsConstructor

public class ProfessorServiceImpl implements ProfessorService {

	private final ProfessorRepository teacherRepository;
	private final SubjectService subjectService;
	private final RoleService roleService;

	@Override
	public Page<ProfessorDto> getAll(Pageable pageable) {
		return teacherRepository.findAll(pageable).map(ProfessorDto::new);
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
	public Long delete(Long id) {
		teacherRepository.findById(id).orElseThrow(() -> new UserNotFoundException("with following Id : " + id));
		teacherRepository.deleteById(id);
		return id;
	}

	@Override
	public List<ProfessorDto> getAll() {
		return teacherRepository.findAll().stream().map(ProfessorDto::new).collect(Collectors.toList());
	}

	@Override
	public Professor professorBuilder(Professor professor, Long id) {
		Professor existingProfessor = new Professor();
		if (id != null) {
			existingProfessor = getById(id).get();
		} else {
			existingProfessor.setLogin(professor.getLogin());
			existingProfessor.setPassword(professor.getPassword());
		}
		List<Subject> subject = new ArrayList<>();
		Subject assignedSubject = subjectService.getById(professor.getSubjects().get(0).getId())
				.orElseThrow(() -> new SubjectNotFoundException("Selected subject was not found"));
		subject.add(assignedSubject);
		
		existingProfessor.setName(professor.getName());
		existingProfessor.setLastName(professor.getLastName());
		existingProfessor.setSubjects(subject);
		existingProfessor.setRole(roleService.getById(3L).get());
		return existingProfessor;
	}
}

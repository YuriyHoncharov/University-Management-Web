package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;

public interface SubjectService {
	
	List<Subject> getAllSubjects();

	Optional<Subject> getSubjectById(Long id);

	Subject saveSubject(Subject subject);

	void deleteSubject(Long id);
}

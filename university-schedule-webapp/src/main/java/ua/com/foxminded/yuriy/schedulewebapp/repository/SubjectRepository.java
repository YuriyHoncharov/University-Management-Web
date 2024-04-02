package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	@Query("SELECT s FROM Subject s WHERE NOT EXISTS (SELECT p FROM Professor p WHERE p.subject = s)")
	List<Subject>findAllUnassignedSubjects();
}



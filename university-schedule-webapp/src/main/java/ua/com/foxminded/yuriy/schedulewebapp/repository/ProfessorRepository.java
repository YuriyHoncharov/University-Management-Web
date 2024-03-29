package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	Page<Professor>findAll (Pageable pageable);
	
	@Query("SELECT p FROM Professor p WHERE p.subject = :subject")
	Professor getBySubject(Subject subject);
}

package ua.com.foxminded.yuriy.schedulewebapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Page<Student>findAll (Pageable pageable);
	
}

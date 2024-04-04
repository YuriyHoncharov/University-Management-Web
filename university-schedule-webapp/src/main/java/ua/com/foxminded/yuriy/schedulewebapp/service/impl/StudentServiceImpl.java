package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schedulewebapp.exception.EntityNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;
import ua.com.foxminded.yuriy.schedulewebapp.service.RoleService;
import ua.com.foxminded.yuriy.schedulewebapp.service.StudentService;
import ua.com.foxminded.yuriy.schedulewebapp.service.SubjectService;
import ua.com.foxminded.yuriy.schedulewebapp.service.YearService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final YearService yearService;
	private final HouseService houseService;
	private final SubjectService subjectService;
	private final RoleService roleService;

	@Override
	public List<StudentDto> getAll() {
		return studentRepository.findAll().stream().map(StudentDto::new).collect(Collectors.toList());
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
	@Transactional
	public Long delete(Long id) {
		String errorMessage = String.format("Student with following ID : %d is not present in DataBase", id);
		studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
		studentRepository.deleteById(id);
		return id;
	}

	@Override
	public Page<StudentDto> findAll(Pageable pageable) {
		Page<Student> pageStudent = studentRepository.findAll(pageable);
		return pageStudent.map(StudentDto::new);
	}

	@Override
	public Student studentBuilder(Student student, Long id) {
		Student existingStudent = new Student();
		if (id != null) {
			existingStudent = getById(id).get();
		} else {
			existingStudent.setLogin(student.getLogin());
			existingStudent.setPassword(student.getPassword());
		}
		Year year = yearService.getById(student.getYear().getId()).get();
		House house = houseService.getById(student.getHouse().getId()).get();
		existingStudent.setHouse(house);
		existingStudent.setYear(year);
		existingStudent.setName(student.getName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setRole(roleService.getById(2L).get());
		return existingStudent;
	}

	@Override
	public Student editStudentSubjects(Long studentId, Long subjectId) {

		Student student = getById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with following ID was not found : " + studentId));
		List<Subject> subjects = student.getSubjects();
		Optional<Subject> subjectToDelete = subjects.stream().filter(subject -> subject.getId().equals(subjectId))
				.findFirst();

		if (subjectToDelete.isPresent()) {
			subjects.remove(subjectToDelete.get());
			student.setSubjects(subjects);
		} else {
			Subject subjectToAdd = subjectService.getById(subjectId)
					.orElseThrow(() -> new EntityNotFoundException("Subject with following ID was not found : " + subjectId));
			subjects.add(subjectToAdd);
			student.setSubjects(subjects);
		}
		return student;
	}
}

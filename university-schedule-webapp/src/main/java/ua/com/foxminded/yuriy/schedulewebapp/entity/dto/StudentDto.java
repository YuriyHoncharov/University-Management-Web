package ua.com.foxminded.yuriy.schedulewebapp.entity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Student;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentDto {
	
	private Long id;
	private String name;
	private String lastName;
	private int year;
	private String house;
	private List<Subject>subjects;
	
	public StudentDto(Student student) {
		this.id = student.getId();
		this.name = student.getName();
		this.lastName = student.getLastName();
		this.year = student.getYear().getYearValue();
		this.house = student.getHouse().getHouse();
		this.subjects = student.getSubjects();
	}
	
}

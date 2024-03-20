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
	private String year;
	private String house;
	private List<Subject> subjects;

	public StudentDto(Student student) {
		this.id = student.getId();
		this.name = student.getName();
		this.lastName = student.getLastName();
		if (student.getYear() != null) {
			this.year = String.valueOf(student.getYear().getYearValue());

		} else {
			this.year = "Waiting for Assignement";
		}
		if (student.getHouse() != null) {
			this.house = String.valueOf(student.getHouse().getHouse());

		} else {
			this.house = "Waiting for Assignement";
		}
		this.subjects = student.getSubjects();
	}

}

package ua.com.foxminded.yuriy.schedulewebapp.entity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto {

	private Long id;
	private String name;
	private String lastName;
	private List<Subject> subjects;

	public ProfessorDto(Professor professor) {
		this.id = professor.getId();
		this.name = professor.getName();
		this.lastName = professor.getLastName();
		this.subjects = professor.getSubjects();
	}
}

package ua.com.foxminded.yuriy.schedulewebapp.entity.dto;

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
	private Subject subject;

	public ProfessorDto(Professor professor) {
		this.id = professor.getId();
		this.name = professor.getName();
		this.lastName = professor.getLastName();
		this.subject = professor.getSubject();
	}
}

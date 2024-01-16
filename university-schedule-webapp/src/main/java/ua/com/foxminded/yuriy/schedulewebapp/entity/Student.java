package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("3")

public class Student extends User {

	@ManyToOne
	@JoinColumn(name = "gradeId")
	private Grade grade;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
	private List<Subject> subjects;
}

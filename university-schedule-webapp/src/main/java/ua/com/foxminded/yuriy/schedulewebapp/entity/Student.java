package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Students")
public class Student extends User {

	@ManyToOne
	@JoinColumn(name = "gradeId")
	private Grade grade;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
	private List<Subject> subjects;
}

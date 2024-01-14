package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Teachers")

public class Teacher extends User {

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
	private List<Subject> subjects;
	
}

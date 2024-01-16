package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("2")

public class Teacher extends User {
	
	

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
	private List<Subject> subjects;

	public Teacher() {
	}	
	
}

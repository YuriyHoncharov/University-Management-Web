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
@DiscriminatorValue("2")
public class Student extends Wizard {
	@ManyToOne
	@JoinColumn(name = "houseId")
	private House house;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "wizardId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
	private List<Subject> subjects;
}

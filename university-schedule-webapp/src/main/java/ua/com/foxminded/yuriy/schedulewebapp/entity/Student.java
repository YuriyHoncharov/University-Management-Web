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
	@JoinColumn(name = "house_id")
	private House house;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "wizard_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects;

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}

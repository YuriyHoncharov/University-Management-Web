package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("3")

public class Professor extends Wizard {
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "wizardId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
	private List<Subject> subjects;

	public Professor() {
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}

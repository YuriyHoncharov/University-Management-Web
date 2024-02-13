package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("3")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Professor extends Wizard {
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "wizard_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects;	
}

package ua.com.foxminded.yuriy.schedulewebapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("3")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Professor extends Wizard {

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinTable(name = "Enrollments", joinColumns = @JoinColumn(name = "wizard_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	@JsonManagedReference
	private Subject subject;

}

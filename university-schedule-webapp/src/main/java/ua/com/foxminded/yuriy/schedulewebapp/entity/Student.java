package ua.com.foxminded.yuriy.schedulewebapp.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Students")
public class Student extends User {
	
	@ManyToOne
	@JoinColumn(name = "gradeId")
	private Grade grade;

}

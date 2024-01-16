package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Grades")

public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "grade", nullable = false, length = 5)
	private String grade;

	@OneToMany(mappedBy = "grade", cascade = CascadeType.REMOVE)
	private List<Lesson> lessons = new ArrayList<>();

	public Grade() {
	}

	public Grade(Long id, String grade) {
		this.id = id;
		this.grade = grade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return grade;
	}

	public void setValue(String grade) {
		this.grade = grade;
	}

}

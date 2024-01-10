package ua.com.foxminded.yuriy.schedulewebapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Lessons")
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "subjectId", nullable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "teacherId", nullable = false)
	private Teacher teacher;

	@Column(name = "time", nullable = false)
	private Timestamp time;

	@ManyToOne
	@JoinColumn(name = "auditoriumId", nullable = false)
	private Auditorium auditorium;

	@ManyToOne
	@JoinColumn(name = "gradeId", nullable = false)
	private Grade grade;
}

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private Professor professor;

	@Column(name = "time", nullable = false)
	private Timestamp time;

	@ManyToOne
	@JoinColumn(name = "auditorium_id", nullable = false)
	private Auditorium auditorium;

	@ManyToOne
	@JoinColumn(name = "house_id", nullable = false)
	private House house;

	@ManyToOne
	@JoinColumn(name = "year_id")
	private Year year;

}

package ua.com.foxminded.yuriy.schedulewebapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Years")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Year {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "yearValue", nullable = false)
	private int yearValue;

}

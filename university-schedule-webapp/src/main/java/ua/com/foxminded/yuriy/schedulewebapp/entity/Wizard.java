package ua.com.foxminded.yuriy.schedulewebapp.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Wizards")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_id", discriminatorType = DiscriminatorType.INTEGER)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Wizard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "year_id")
	private Year year;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "house_id")
	private House house;

}

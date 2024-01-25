package ua.com.foxminded.yuriy.schedulewebapp.entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Headmaster extends User {
}

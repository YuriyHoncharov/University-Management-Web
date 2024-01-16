package ua.com.foxminded.yuriy.schedulewebapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("1")

public class Admin extends User{

}

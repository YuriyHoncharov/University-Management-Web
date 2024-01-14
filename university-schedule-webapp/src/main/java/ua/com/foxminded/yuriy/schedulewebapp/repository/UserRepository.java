package ua.com.foxminded.yuriy.schedulewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.yuriy.schedulewebapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

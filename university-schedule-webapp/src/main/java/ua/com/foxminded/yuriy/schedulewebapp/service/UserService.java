package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.User;

public interface UserService {
	
	List<User> getAllUsers();
	Optional<User> getUserById(Long id);
	User saveUser(User user);
	void deleteUser(Long id);
}

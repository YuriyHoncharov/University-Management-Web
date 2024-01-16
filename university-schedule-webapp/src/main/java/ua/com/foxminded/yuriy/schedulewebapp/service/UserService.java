package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.User;

public interface UserService {
	
	List<User> getAll();
	
	Optional<User> getById(Long id);
	
	User save(User user);
	
	void delete(Long id);
}

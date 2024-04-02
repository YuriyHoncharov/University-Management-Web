package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;

public interface RoleService {

	List<Role> getAll();
	Optional<Role> getById(Long id);
	Role save(Role role);
	void delete(Long id);
}

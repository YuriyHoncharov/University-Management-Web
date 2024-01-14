package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;

public interface RoleService {

	List<Role> getAllRoles();

	Optional<Role> getRoleById(Long id);

	Role saveRole(Role role);

	void deleteRole(Long id);
}

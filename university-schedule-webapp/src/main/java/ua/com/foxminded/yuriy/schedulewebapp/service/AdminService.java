package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Admin;

public interface AdminService {

	List<Admin> getAllAdmins();

	Optional<Admin> getAdminById(Long id);

	Admin saveAdmin(Admin admin);

	void deleteAdmin(Long id);

}

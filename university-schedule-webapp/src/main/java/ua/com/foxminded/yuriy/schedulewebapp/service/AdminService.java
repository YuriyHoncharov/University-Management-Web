package ua.com.foxminded.yuriy.schedulewebapp.service;

import java.util.List;
import java.util.Optional;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Admin;

public interface AdminService {

	List<Admin> getAll();

	Optional<Admin> getById(Long id);

	Admin save(Admin admin);

	void delete(Long id);

}

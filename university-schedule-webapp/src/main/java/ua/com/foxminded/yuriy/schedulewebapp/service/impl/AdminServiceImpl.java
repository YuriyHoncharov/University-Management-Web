package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Admin;
import ua.com.foxminded.yuriy.schedulewebapp.repository.AdminRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminRepository adminRepository;

	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public List<Admin> getAll() {
		return adminRepository.findAll();
	}

	@Override
	public Optional<Admin> getById(Long id) {
		return adminRepository.findById(id);
	}

	@Override
	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public void delete(Long id) {
		adminRepository.deleteById(id);
	}
}

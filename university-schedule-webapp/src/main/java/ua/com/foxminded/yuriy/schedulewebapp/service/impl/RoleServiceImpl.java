package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Role;
import ua.com.foxminded.yuriy.schedulewebapp.repository.AuditoriumRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.RoleRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.RoleService;

@Service
@RequiredArgsConstructor

public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}
}

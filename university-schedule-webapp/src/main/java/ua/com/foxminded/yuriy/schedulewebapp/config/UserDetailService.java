package ua.com.foxminded.yuriy.schedulewebapp.config;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Wizard;
import ua.com.foxminded.yuriy.schedulewebapp.exception.UserNotFoundException;
import ua.com.foxminded.yuriy.schedulewebapp.repository.WizardRepository;

@Service
public class UserDetailService implements UserDetailsService {

	private final WizardRepository wizardRepository;

	public UserDetailService(WizardRepository wizardRepository) {
		this.wizardRepository = wizardRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Wizard wizard = wizardRepository.findByLogin(login);
		if (wizard == null) {
			throw new UserNotFoundException("User with such login was not found : " + login);
		}
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + wizard.getRole());
		return new org.springframework.security.core.userdetails.User(wizard.getLogin(), wizard.getPassword(),
				Collections.singleton(grantedAuthority));
	}
}

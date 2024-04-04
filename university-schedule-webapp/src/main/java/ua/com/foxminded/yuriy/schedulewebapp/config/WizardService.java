package ua.com.foxminded.yuriy.schedulewebapp.config;

import java.util.Collections;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Wizard;
import ua.com.foxminded.yuriy.schedulewebapp.repository.WizardRepository;

@Service
@RequiredArgsConstructor
public class WizardService implements UserDetailsService {

	private final WizardRepository wizardRepository;
		
	public Optional<Wizard> findByLogin(String login) {
		return wizardRepository.findByLogin(login);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Wizard wizard = findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + wizard.getRole().getName().toUpperCase());
		return new org.springframework.security.core.userdetails.User(wizard.getLogin(), wizard.getPassword(),
				Collections.singleton(grantedAuthority));
	}	
}

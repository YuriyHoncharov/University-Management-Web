package ua.com.foxminded.yuriy.schedulewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Wizard;

public interface WizardRepository extends JpaRepository<Wizard, Long> {
	Wizard findByLogin(String login);
}

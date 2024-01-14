package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;
import ua.com.foxminded.yuriy.schedulewebapp.repository.AuditoriumRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.AuditoriumService;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	private final AuditoriumRepository auditoriumRepository;

	@Autowired
	public AuditoriumServiceImpl(AuditoriumRepository auditoriumRepository) {
		this.auditoriumRepository = auditoriumRepository;
	}

	@Override
	public List<Auditorium> getAllAuditoriums() {
		return auditoriumRepository.findAll();
	}

	@Override
	public Optional<Auditorium> getAuditoriumById(Long id) {
		return auditoriumRepository.findById(id);
	}

	@Override
	public Auditorium saveAuditorium(Auditorium auditorium) {
		return auditoriumRepository.save(auditorium);
	}

	@Override
	public void deleteAuditorium(Long id) {
		auditoriumRepository.deleteById(id);
	}
}

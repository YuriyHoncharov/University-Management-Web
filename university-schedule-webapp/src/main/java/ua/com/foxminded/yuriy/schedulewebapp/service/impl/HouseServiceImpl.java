package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.repository.AuditoriumRepository;
import ua.com.foxminded.yuriy.schedulewebapp.repository.HouseRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;

@Service
@RequiredArgsConstructor

public class HouseServiceImpl implements HouseService {

	private final HouseRepository houseRepository;

	@Override
	public List<House> getAll() {
		return houseRepository.findAll();
	}

	@Override
	public Optional<House> getById(Long id) {
		return houseRepository.findById(id);
	}

	@Override
	public House save(House house) {
		return houseRepository.save(house);
	}

	@Override
	public void delete(Long id) {
		houseRepository.deleteById(id);
	}
}

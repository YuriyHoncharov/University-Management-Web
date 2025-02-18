package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;

import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;

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
	    Optional<House> alreadyExistingHouse = houseRepository.findByHouse(house.getHouse());
	    alreadyExistingHouse.ifPresent(existingHouse -> {
	        throw new ValidationException("House with the following name already exists");
	    });	    	    
	    return houseRepository.save(house);
	}

	@Override
	public Long delete(Long id) {
		houseRepository.deleteById(id);
		return id;
	}

	@Override
	public Optional<House> getByHouse(String house) {
		return houseRepository.findByHouse(house);
	}

	@Override
	public Page<House> findAll(Pageable pageable) {
		return houseRepository.findAll(pageable);
	}

}

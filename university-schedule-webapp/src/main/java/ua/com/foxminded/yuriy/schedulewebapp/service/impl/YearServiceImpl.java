package ua.com.foxminded.yuriy.schedulewebapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;
import ua.com.foxminded.yuriy.schedulewebapp.repository.YearRepository;
import ua.com.foxminded.yuriy.schedulewebapp.service.YearService;

@Service
@RequiredArgsConstructor

public class YearServiceImpl implements YearService {
	
	
	private final YearRepository yearRepository;

	@Override
	public List<Year> getAll() {
		return yearRepository.findAll();
	}

	@Override
	public Optional<Year> getById(Long id) {
		return yearRepository.findById(id);
	}

}

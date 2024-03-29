package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.foxminded.yuriy.schedulewebapp.entity.House;

public interface HouseRepository extends JpaRepository<House, Long>{
	
	Optional<House> findByHouse(String houseName);
	
	Page<House> findAll (Pageable pageable);
	
}

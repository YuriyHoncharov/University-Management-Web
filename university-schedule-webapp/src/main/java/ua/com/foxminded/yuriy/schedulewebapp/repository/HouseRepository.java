package ua.com.foxminded.yuriy.schedulewebapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.foxminded.yuriy.schedulewebapp.entity.House;

public interface HouseRepository extends JpaRepository<House, Long>{
	
	@Query("SELECT h FROM House h WHERE h.house = :houseName")
	Optional<House> getByHouseName(String houseName);
	
}

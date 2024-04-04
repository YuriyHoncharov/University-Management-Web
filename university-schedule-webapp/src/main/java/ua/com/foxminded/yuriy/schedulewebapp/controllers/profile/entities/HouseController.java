package ua.com.foxminded.yuriy.schedulewebapp.controllers.profile.entities;

import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import lombok.AllArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.exception.ValidationException;
import ua.com.foxminded.yuriy.schedulewebapp.service.HouseService;

@RestController
@AllArgsConstructor
@RequestMapping("/profile/dashboard/houses")
public class HouseController {

	private HouseService houseService;

	@GetMapping
	public ModelAndView getHousePage(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
		ModelAndView mav = new ModelAndView();
		Page<House> houses = houseService.findAll(PageRequest.of(page, 2));
		mav.addObject("houses", houses);
		mav.addObject("numbers", IntStream.range(1, houses.getTotalPages()).toArray());
		mav.setViewName("profile/entities/houses");
		return mav;
	}

	@GetMapping("/create")
	public ModelAndView showCreateView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile/entities/create/houseCreate");
		return mav;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditView(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		return houseService.getById(id).map(house -> {
			mav.addObject("house", house);
			mav.setViewName("profile/entities/edit/houseEdit");
			return mav;
		}).orElseGet(() -> {
			mav.setViewName("redirect:profile/entities/houses");
			return mav;
		});
	}

	@DeleteMapping("/delete/{id}")

	public ResponseEntity<String> deleteHouse(@PathVariable Long id) {
		try {
			houseService.delete(id);
			return new ResponseEntity<>("House deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to delete the house : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@RequestBody House house) {

		try {
			House updatedHouse = houseService.save(house);
			return ResponseEntity.ok(updatedHouse);

		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody House house) {
		try {
			House newHouse = houseService.save(house);
			return ResponseEntity.ok(newHouse);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

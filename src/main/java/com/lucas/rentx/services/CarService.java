package com.lucas.rentx.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.dto.CarResponseDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.entities.QCar;
import com.lucas.rentx.repositories.CarRepository;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;
import com.querydsl.core.BooleanBuilder;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CategoryService categoryService;

	public Car find(UUID id) {
		Optional<Car> obj = carRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	@Transactional()
	public CarDTO insert(CarDTO obj, UUID id) {
		Car car = fromDto(obj);
		car.setCategory(categoryService.find(id));
		carRepository.save(car);
		return new CarDTO(car);
	}

	@Transactional(readOnly = true)
	public Page<CarResponseDTO> listAvailable(String name, String brand, UUID categoryId, Pageable pageable) {
		QCar entity = QCar.car;
		BooleanBuilder where = new BooleanBuilder();

		where.and(entity.avaiable.eq(true));

		if (brand != null) {
			where.and(entity.brand.eq(brand));
		}

		if (name != null) {
			where.and(entity.name.eq(name));
		}

		if (categoryId != null) {
			where.and(entity.category.id.eq(categoryId));
		}

		Page<Car> list = carRepository.findAll(where, pageable);
		Page<CarResponseDTO> listDto = list.map(obj -> new CarResponseDTO(obj));

		return listDto;
	}

	public Car fromDto(CarDTO objDto) {
		return new Car(null, objDto.getName(), objDto.getDescription(), objDto.getDaily_rate(), true,
				objDto.getLicense_plate(), objDto.getFine_amount(), objDto.getBrand(), null, null);
	}
}

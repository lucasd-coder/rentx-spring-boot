package com.lucas.rentx.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.rentx.dto.SpecificationCarDTO;
import com.lucas.rentx.dto.SpecificationCarIdDTO;
import com.lucas.rentx.entities.SpecificationCar;
import com.lucas.rentx.repositories.SpecificationCarRepository;

@Service
public class SpecificationCarService {

	@Autowired
	private SpecificationService specificationService;

	@Autowired
	private SpecificationCarRepository specificationCarRepository;

	@Autowired
	private CarService carService;

	@Transactional()
	public SpecificationCarDTO insert(UUID car_id, SpecificationCarIdDTO specification_id) {
		SpecificationCar obj = new SpecificationCar();

		for(UUID id : specification_id.getSpecificationId()) {
			obj.setSpecification(this.specificationService.find(id));
		}
		
		obj.setCar(this.carService.find(car_id));
		obj.setCreatedAt(LocalDateTime.now());

		specificationCarRepository.save(obj);

		return new SpecificationCarDTO(obj);
	}

}

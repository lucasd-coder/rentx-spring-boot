package com.lucas.rentx.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.rentx.dto.SpecificationDTO;
import com.lucas.rentx.entities.Specification;
import com.lucas.rentx.repositories.SpecificationRepository;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class SpecificationService {

	@Autowired
	private SpecificationRepository specificationRepository;
	
	public Specification find(UUID id) {
		Optional<Specification> obj = specificationRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ""));
	}	
		
	@Transactional()
	public SpecificationDTO insert(SpecificationDTO objDto) {
		Specification specification = fromDTO(objDto);
		specification = specificationRepository.save(specification);
		
		return new SpecificationDTO(specification);		
	}	
	
	public Specification fromDTO(SpecificationDTO objDto) {
		return new Specification(null, objDto.getName(), objDto.getDescription(), null);
	}
}

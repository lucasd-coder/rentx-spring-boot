package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, UUID>{

}

package com.lucas.rentx.tests;

import java.time.LocalDateTime;
import java.util.UUID;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.dto.CarResponseDTO;
import com.lucas.rentx.dto.CredenciaisDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.entities.Category;
import com.lucas.rentx.entities.User;

public class Factory {

	public static User createUser() {
		return new User(UUID.fromString("420ee4ba-6eec-4f14-a237-14cc972d0494"), "zedasilva", "ze1234",
				"zedasilva@gmail.com", "123456", "7891011", "avatar.png", LocalDateTime.now());
	}

	public static Car createCar() {
		return new Car(UUID.fromString("55ae7f39-0ac9-4386-a315-c07ae02ec567"), "Nome Car", "Description Car", 100,
				true, "ABC-1234", 50, "Brand", LocalDateTime.now(), createCategory());
	}

	public static Category createCategory() {
		return new Category(UUID.fromString("cf4957be-8b59-40bd-8681-7dc2a50ee570"), "Ford", "category test",
				LocalDateTime.now());
	}

	public static CarDTO createCarDTORequest() {
		return new CarDTO(UUID.randomUUID(), "Nome Car3", "Description Car3", 300,
				100, "ABC-600", "Brand3", createCategory().getId(), LocalDateTime.now());
	}
	
	public static CarDTO createCarDTO() {
		return new CarDTO(UUID.fromString("db155f29-1972-4e8b-adec-c8c1bc089e28"), "Nome Car2", "Description Car2", 200,
				90, "ABC-593", "Brand2", createCategory().getId(), LocalDateTime.now());
	}
	
	public static CarResponseDTO createCarResponseDTO() {
		Car car = createCar();
		return new CarResponseDTO(car);
	}
	
	public static CredenciaisDTO createCredenciaisDTOUser() {
		return new CredenciaisDTO("zedasilva@gmail.com", "123456");
	}
	
	public static CredenciaisDTO createCredenciaisDTOAdmin() {
		return new CredenciaisDTO("admin@gmail.com", "123456");
	}
}

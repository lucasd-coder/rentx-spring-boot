package com.lucas.rentx.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.rentx.entities.Car;
import com.lucas.rentx.entities.Category;
import com.lucas.rentx.entities.Specification;
import com.lucas.rentx.entities.SpecificationCar;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.entities.enums.Perfil;
import com.lucas.rentx.repositories.CarRepository;
import com.lucas.rentx.repositories.CategoryRepository;
import com.lucas.rentx.repositories.SpecificationCarRepository;
import com.lucas.rentx.repositories.SpecificationRepository;
import com.lucas.rentx.repositories.UserRepository;

@Service
public class SeedService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private SpecificationRepository specificationRepository;		
	
	@Autowired
	private SpecificationCarRepository specificationCarRepository;

	public void instantiateTestDatabase() throws ParseException {

		User user1 = new User(null, "Maria", "maria123", pe.encode("123456"), "maria@gmail.com", "123456789", null,
				null);
		User admin = new User(null, "João", "joao123", pe.encode("123456"), "joao@gmail.com", "123456789", null, null);
		admin.addPerfil(Perfil.ADMIN);

		userRepository.saveAll(Arrays.asList(user1, admin));

		Category cat1 = new Category(null, "SUVs",
				"SUVs da atualidade (compactos, médios e grandes) "
						+ "são feitos com carroceria monobloco, mas há também SUVs c"
						+ "onstruídos sobre chassi, derivados de picape",
				null);

		Category cat2 = new Category(null, "Cupês", "Os cupês ou coupés são carros de carroceria mais esportiva, "
				+ "normalmente de duas portas, embora hoje existam os “cupês de quatro portas”. "
				+ "São carros de altura reduzida, em que as portas geralmente não possuem moldura para a janela. Em geral, "
				+ "são carros em que a emoção prevalece sobre a razão.", null);

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));

		Car car1 = new Car(null, "fusca", "fusca azul", 20, true, "VAR-2005", 10, "Volkswagen", null, cat1);

		Car car2 = new Car(null, "Fiorino", "um membro da família Uno", 40, true, "OUT-147", 25, "Fiat", null, cat2);
		
		Car car3 = new Car(null, "saveiro", "carro bonito", 45, false, "UTU-200", 30, "Volkswagen", null, cat2);
		
		Car car4 = new Car(null, "audi A6", ".0 55 TFSI GASOLINA PERFORMANCE BLACK QUATTRO 4P S-TRONIC", 60, false, "YTR-88", 35, "Audi", null, cat1);

		carRepository.saveAll(Arrays.asList(car1, car2, car3, car4));
		
		Specification spe1 = new Specification(null, "2 portas", "carro bonito", null);
		
		Specification spe2 = new Specification(null, "2 portas", "carro com pneu goodyear", null);
		
		specificationRepository.saveAll(Arrays.asList(spe1, spe2));
		
		SpecificationCar specar1 = new SpecificationCar(car1, spe1, null);
		SpecificationCar specar2 = new SpecificationCar(car2, spe2, null);
		SpecificationCar specar3 = new SpecificationCar(car2, spe1, null);
		
		car1.getSpecifications().addAll(Arrays.asList(specar1));
		car2.getSpecifications().addAll(Arrays.asList(specar2, specar3));
		
		spe1.getSpecifications().addAll(Arrays.asList(specar1, specar2));
		spe2.getSpecifications().addAll(Arrays.asList(specar3));
		
		specificationCarRepository.saveAll(Arrays.asList(specar1, specar2, specar3));
				
	}

}

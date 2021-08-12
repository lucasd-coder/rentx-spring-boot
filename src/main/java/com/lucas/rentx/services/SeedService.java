package com.lucas.rentx.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.rentx.entities.Category;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.CategoryRepository;
import com.lucas.rentx.repositories.UserRepository;

@Service
public class SeedService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private CategoryRepository categoryRepository;

	public void instantiateTestDatabase() throws ParseException {

		User user1 = new User(null, "Maria", "maria123", pe.encode("123456"), "maria@gmail.com", "123456789", null,
				null);
		User admin = new User(null, "João", "joao123", pe.encode("123456"), "joao@gmail.com", "123456789", null, null);

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

	}

}

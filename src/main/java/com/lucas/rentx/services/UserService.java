package com.lucas.rentx.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.rentx.dto.UserDTO;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;
import com.lucas.rentx.security.UserSS;
import com.lucas.rentx.services.exceptions.AuthorizationException;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocalStoreService localStoreService;

	@Autowired
	private BCryptPasswordEncoder pe;

	public User find(UUID id) {
		UserSS user = UserAuthService.authenticated();
		if (user == null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ""));
	}

	public User insert(User obj) {
		obj.setId(null);
		return userRepository.save(obj);
	}

	public User uploadAvatar(MultipartFile file) {
		UserSS user = UserAuthService.authenticated();
		User checkUserExist = find(user.getId());
		if(checkUserExist.getAvatar() != null) {
			localStoreService.delete(checkUserExist.getAvatar());
		}
		String avatar = localStoreService.salvarAvatar(file);
		checkUserExist.setAvatar(avatar);		
		return userRepository.save(checkUserExist);

	}

	public User fromDto(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getUsername(), pe.encode(objDto.getPassword()),
				objDto.getEmail(), objDto.getDriver_license(), null, null);
	}
}

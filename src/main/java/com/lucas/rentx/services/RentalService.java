package com.lucas.rentx.services;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.rentx.dto.RentalCustomBodyDTO;
import com.lucas.rentx.dto.RentalDTO;
import com.lucas.rentx.entities.Rental;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.RentalRepository;
import com.lucas.rentx.repositories.querys.CarRepositoriyQuery;
import com.lucas.rentx.security.UserSS;
import com.lucas.rentx.services.exceptions.DataIntegrityException;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CarRepositoriyQuery carRepositoriyQuery;
	
	@Autowired
	private CarService carService;

	@Transactional()
	public RentalDTO insert(RentalCustomBodyDTO objDto) {
		long mininunHour = 24;
		Rental rental = new Rental();
		
		UserSS user = UserAuthService.authenticated();
		User checkUserExist = userService.find(user.getId());

		long compare = compareInHours(new Date(), objDto.getExpectedReturnDate());

		if (compare < mininunHour) {
			throw new DataIntegrityException("Invalid return time!");
		}
		rental.setCar(carService.find(objDto.getCarId()));
		rental.setUser(checkUserExist);
		rental.setExpectedReturnDate(objDto.getExpectedReturnDate());

		rentalRepository.save(rental);

		carRepositoriyQuery.updateAvailable(objDto.getCarId(), false);

		return new RentalDTO(rental);
	}

	private Long compareInHours(Date startDate, Date endDate) {
		Date diff = new Date(endDate.getTime() - startDate.getTime());
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		cal.setTime(diff);
		long hours = cal.get(Calendar.HOUR_OF_DAY);

		return hours;
	}
}

package com.lucas.rentx.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.rentx.dto.RentalDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.entities.Rental;
import com.lucas.rentx.repositories.RentalRepository;
import com.lucas.rentx.repositories.querys.impl.CarRepositoriyQueryImpl;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class DevolutionRentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private CarRepositoriyQueryImpl carRepositoriyQueryImpl;

	@Autowired
	private CarService carService;

	public Rental find(UUID id) {
		Optional<Rental> obj = rentalRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ""));
	}

	@Transactional()
	public RentalDTO devolutionRental(UUID id) {
		Rental rental = find(id);
		Car car = carService.find(rental.getCar().getId());

		long minimum_daily = 1;
		Date dateNow = new Date();

		long daily = compareInDays(rental.getStartDate(), new Date());

		if (daily <= 0) {
			daily = minimum_daily;
		}

		long delay = compareInDays(dateNow, rental.getExpectedReturnDate());

		long total = 0;

		if (delay > 0) {
			long calculate_fine = delay * car.getFineAmount();
			total = calculate_fine;
		}

		total += delay * car.getDailyRate();
		rental.setEndDate(new Date());
		rental.setTotal(total);

		rentalRepository.save(rental);
		carRepositoriyQueryImpl.updateAvailable(car.getId(), true);

		return new RentalDTO(rental);

	}

	private Long compareInDays(Date startDate, Date endDate) {
		Date diff = new Date(endDate.getTime() - startDate.getTime());
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		cal.setTime(diff);
		long hours = cal.get(Calendar.DAY_OF_WEEK);

		return hours;
	}

}

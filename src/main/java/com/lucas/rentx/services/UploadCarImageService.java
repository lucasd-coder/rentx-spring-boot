package com.lucas.rentx.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.rentx.entities.CarImage;
import com.lucas.rentx.repositories.CarImageRepository;

@Service
public class UploadCarImageService {

	@Value("${tmp.disco.raiz-cars}")
	private String raizCar;

	@Value("${tmp.disco.diretorio-cars}")
	private String diretorioCar;

	@Autowired
	private CarImageRepository carImageRepository;

	@Autowired
	private CarService carService;

	@Autowired
	private LocalStoreService localStoreService;

	public void upoloadImageCar(UUID carId, List<MultipartFile> imageName) {

		for (MultipartFile file : imageName) {
			CarImage carImage = new CarImage();
			carImage.setCar(carService.find(carId));
			String image = localStoreService.salvar(file, raizCar, diretorioCar);
			carImage.setImageName(image);
			carImageRepository.save(carImage);
		}

	}

}

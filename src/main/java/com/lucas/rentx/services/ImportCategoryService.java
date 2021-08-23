package com.lucas.rentx.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.rentx.dto.CategoryDTO;
import com.lucas.rentx.entities.Category;
import com.lucas.rentx.repositories.CategoryRepository;
import com.lucas.rentx.services.exceptions.DataIntegrityException;
import com.lucas.rentx.services.exceptions.FileException;

@Service
public class ImportCategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO loadCategory(MultipartFile file) {

		String ext = FilenameUtils.getExtension(file.getOriginalFilename());

		if (!"csv".equals(ext)) {
			throw new FileException("Somente arquivos csv são permitidas");
		}

		Category category = new Category();

		try {

			Reader reader = new InputStreamReader(new BOMInputStream(file.getInputStream()), StandardCharsets.UTF_8);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

			for (CSVRecord record : records) {
				String name = record.get(0);
				String description = record.get(1);
				Category aux = categoryRepository.findByName(name);

				if (aux != null) {
					throw new DataIntegrityException(aux.getName() + " " + "category já existe");
				}
				category.setId(null);
				category.setName(name);
				category.setDescription(description);

				categoryRepository.save(category);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new FileException("Erro ao ler arquivo");
		}

		return new CategoryDTO(category);

	}

}

package com.lucas.rentx.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.rentx.services.exceptions.FileException;

@Service
public class LocalStoreService {

	@Value("${tmp.disco.raiz}")
	private String raiz;

	@Value("${tmp.disco.diretorio-avatar}")
	private String diretorioAvatar;

	public String salvarAvatar(MultipartFile avatar) {

		return this.salvar(this.diretorioAvatar, avatar);
	}

	public String salvar(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

		try {

			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			return arquivo.getOriginalFilename();

		} catch (IOException e) {
			throw new FileException("Problemas na tentativa de salvar arquivo.");
		}
	}
}

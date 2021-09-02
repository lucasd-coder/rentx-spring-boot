package com.lucas.rentx.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.rentx.services.exceptions.FileException;

@Service
public class LocalStoreService {

	public String salvar(MultipartFile arquivo, String raiz, String diretorio) {
		Path diretorioPath = Paths.get(raiz, diretorio);
		String file = UUID.randomUUID() + "-" + arquivo.getOriginalFilename();
		Path arquivoPath = diretorioPath.resolve(file);

		try {

			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			return file;

		} catch (IOException e) {
			throw new FileException("Problemas na tentativa de salvar arquivo.");
		}
	}

	public void delete(String arquivo, String raiz, String diretorioAvatar) {
		Path diretorioPath = Paths.get(raiz, diretorioAvatar);
		Path arquivoPath = diretorioPath.resolve(arquivo);
		File removeAvatar = new File(arquivoPath.toString());
		removeAvatar.delete();
	}

}

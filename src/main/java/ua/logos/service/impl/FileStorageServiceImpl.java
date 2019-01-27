package ua.logos.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ua.logos.exception.FileStorageException;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService{
	//create path
	private final String PATH = System.getProperty("user.dir");
	private final String SEPARATOR = System.getProperty("file.separator");
	
	private final Path fileStorageLocation;
	//1 get path
	//2 create directory(folder)
	public FileStorageServiceImpl() {
		String uploadDir = PATH + SEPARATOR+"uploads";
		
		this.fileStorageLocation=
				Paths.get(uploadDir).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String storeFile(MultipartFile file) {
		String fileName= StringUtils.cleanPath(file.getOriginalFilename());
		Path targetLocation=null;
		
		try {
			targetLocation = this.fileStorageLocation.resolve(fileName);
			//
			Files.copy(file.getInputStream(),
				targetLocation,StandardCopyOption.REPLACE_EXISTING);
			//
			System.out.println(targetLocation.toString());
			
		}catch (Exception e) {
			throw new FileStorageException("Couldnt save file");
			
		}
		return fileName;
	}

	@Override
	public Resource readFile(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName);
			System.out.println(filePath.toString());
			Resource resource = new UrlResource(filePath.toUri());
			
				if(resource.exists()) return resource;
				else throw new ResourceNotFoundException("file not found");
			
		} catch (MalformedURLException e) {
			throw new ResourceNotFoundException("file not found");// TODO: handle exception
		}
		
	}

}

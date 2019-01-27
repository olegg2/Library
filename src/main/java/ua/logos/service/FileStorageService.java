package ua.logos.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
String storeFile(MultipartFile file);
	
	Resource readFile(String fileName);
	//public boolean checkIfExists();

}

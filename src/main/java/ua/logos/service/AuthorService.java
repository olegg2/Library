package ua.logos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.logos.domain.AuthorDTO;

@Service
public interface AuthorService {
	void saveAuthor(AuthorDTO dto);
	
	AuthorDTO findAuthorById(Long id);
	
	boolean existsByEmail(String email);
	
	List<AuthorDTO> findAllAuthors();
	
	void update(AuthorDTO dto);
	
	void uploadImg(MultipartFile file , String authorId);
	
	boolean checkIfExists();
	
	void delete();
	
	void deleteSelected(String name);
}

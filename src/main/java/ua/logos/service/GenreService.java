package ua.logos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.logos.domain.GenreDTO;

@Service
public interface GenreService {
	void saveGenre(GenreDTO genre);
	
	GenreDTO findGenreById(Long id);
	
	List<GenreDTO> findAllGenres();
	public boolean checkIfExists();
	void delete();
	void deleteSelected(String name);
	
	
	

}

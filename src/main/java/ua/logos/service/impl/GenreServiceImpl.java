package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.GenreDTO;
import ua.logos.entity.GenreEntity;
import ua.logos.repository.GenreRepository;
import ua.logos.service.GenreService;
import ua.logos.utils.ObjectMapperUtils;
@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;

	@Override
	public void saveGenre(GenreDTO genre) {
		GenreEntity entity = objectMapper.map(genre, GenreEntity.class);
		genreRepository.save(entity);
		
	}

	@Override
	public GenreDTO findGenreById(Long id) {
		GenreEntity entity = genreRepository.findById(id).get();
		GenreDTO dto = objectMapper.map(entity,GenreDTO.class);
		return dto;
	}

	@Override
	public List<GenreDTO> findAllGenres() {
		List<GenreEntity> entities = genreRepository.findAll();
		List<GenreDTO> dtos = objectMapper.mapAll(entities, GenreDTO.class);
		return dtos;
	}
	@Override
	public boolean checkIfExists() {
		boolean checker;
		if(genreRepository.count()==0)
			checker=false;
			else checker=true;
		return checker;
	}
	@Override
	public void delete() {
		genreRepository.deleteAll();
		
	}

}

package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.GenreDTO;
import ua.logos.entity.AuthorEntity;
import ua.logos.entity.GenreEntity;
import ua.logos.repository.GenreRepository;
import ua.logos.service.GenreService;
import ua.logos.utils.ObjectMapperUtils;
import ua.logos.utils.StringUtils;
@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;
	@Autowired
	private StringUtils stringUtils;

	@Override
	public void saveGenre(GenreDTO dto) {
		String genreId = stringUtils.generate();
		System.out.println(genreId);
		//String authorId = "jjj33";
		if(!genreRepository.existsByGenreId(genreId)) {
			dto.setGenreId(genreId);
			GenreEntity entity = objectMapper.map(dto,GenreEntity.class);
			genreRepository.save(entity);
		}
		
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

	@Override
	public void deleteSelected(String name) {
		Long id = genreRepository.findByNameOfGenre(name).getId();
		System.out.println("name of selected author : "+name+" id : " + id);
		genreRepository.deleteById(id);
		
	}

}

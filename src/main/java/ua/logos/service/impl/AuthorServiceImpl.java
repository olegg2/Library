package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import antlr.StringUtils;
import ua.logos.domain.AuthorDTO;
import ua.logos.entity.AuthorEntity;
import ua.logos.repository.AuthorRepository;
import ua.logos.service.AuthorService;
import ua.logos.service.cloudinary.CloudinaryService;
import ua.logos.utils.ObjectMapperUtils;
@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;
	@Autowired
	private ua.logos.utils.StringUtils stringUtils;
	@Autowired
	private CloudinaryService cloudinatyService;
	

	@Override
	public void saveAuthor(AuthorDTO dto) {
		String authorId = stringUtils.generate();
		System.out.println(authorId);
		//String authorId = "jjj33";
		if(!authorRepository.existsByAuthorId(authorId)) {
			dto.setAuthorId(authorId);
			AuthorEntity entity = objectMapper.map(dto,AuthorEntity.class);
			authorRepository.save(entity);
		}
		
	}

	@Override
	public AuthorDTO findAuthorById(Long id) {
		AuthorEntity entity= authorRepository.findById(id).get();
		AuthorDTO dto = objectMapper.map(entity, AuthorDTO.class);
		return dto;
	}

	@Override
	public List<AuthorDTO> findAllAuthors() {
		List<AuthorEntity> entities= authorRepository.findAll();
		List<AuthorDTO> dtos = objectMapper.mapAll(entities, AuthorDTO.class);
		return dtos;
	}

	@Override
	public void delete() {
		authorRepository.deleteAll();
		
	}

	@Override
	public void update(AuthorDTO dto) {
		
		
	}
	//upload file on cloudinary service
	@Override
	public void uploadImg(MultipartFile file, String authorId) {
		String imageUrl = cloudinatyService.uploadFile(file, "authors");
		AuthorEntity entity = authorRepository.findByAuthorId(authorId);
		if(entity == null)
			System.out.println("author not found");
		entity.setAuthorImg(imageUrl);
		authorRepository.save(entity);
	}

	@Override
	public boolean existsByEmail(String email) {
		
		return authorRepository.existsByEmail(email);
	}
	@Override
	public boolean checkIfExists() {
		boolean checker;
		if(authorRepository.count()==0)
			checker=false;
			else checker=true;
		return checker;
	}

	
	
	
}

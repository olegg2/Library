package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.TagDTO;
import ua.logos.entity.TagEntity;
import ua.logos.repository.TagRepository;
import ua.logos.service.TagService;
import ua.logos.utils.ObjectMapperUtils;
@Service
public class TagServiceImpl implements TagService{
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;

	@Override
	public void saveTag(TagDTO tag) {
		TagEntity entity = objectMapper.map(tag, TagEntity.class);
		tagRepository.save(entity);
		
	}

	@Override
	public TagDTO findTagById(Long id) {
		TagEntity entity = tagRepository.findById(id).get();
		TagDTO dto = objectMapper.map(entity, TagDTO.class);
		return dto;
	}

	@Override
	public List<TagDTO> findAllTag() {
		List<TagEntity> entities = tagRepository.findAll();
		List<TagDTO> dtos = objectMapper.mapAll(entities, TagDTO.class);
		return dtos;
	}
	@Override
	public boolean checkIfExists() {
		boolean checker;
		if(tagRepository.count()==0)
			checker=false;
			else checker=true;
		return checker;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}

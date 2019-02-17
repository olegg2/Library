package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.TagDTO;
import ua.logos.entity.TagEntity;
import ua.logos.repository.TagRepository;
import ua.logos.service.TagService;
import ua.logos.utils.ObjectMapperUtils;
import ua.logos.utils.StringUtils;
@Service
public class TagServiceImpl implements TagService{
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;
	@Autowired
	private StringUtils stringUtils;

	@Override
	public void saveTag(TagDTO dto) {
		String tagId = stringUtils.generate();
		
		if(!tagRepository.existsByTagId(tagId)) {
			dto.setTagId(tagId);
			TagEntity entity = objectMapper.map(dto, TagEntity.class);
			tagRepository.save(entity);
		}
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

	@Override
	public void deleteSelected(String name) {
		Long id = tagRepository.findByNameOfTag(name).getId();
		System.out.println("name of selected tag : "+name+" id : " + id);
		tagRepository.deleteById(id);
	}

}

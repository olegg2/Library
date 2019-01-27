package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.DescriptionDTO;
import ua.logos.entity.DescriptionEntity;
import ua.logos.repository.DescriptionRepository;
import ua.logos.service.DescriptionService;
import ua.logos.utils.ObjectMapperUtils;
@Service
public class DescriptionServiceImpl implements DescriptionService{
	@Autowired
	private DescriptionRepository descriptionRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;

	@Override
	public void saveDescription(DescriptionDTO description) {
		DescriptionEntity entity = objectMapper.map(description, DescriptionEntity.class);
		descriptionRepository.save(entity);
		
	}

	@Override
	public DescriptionDTO findDescriptionById(Long id) {
		DescriptionEntity entity= descriptionRepository.findById(id).get();
		DescriptionDTO dto = objectMapper.map(entity, DescriptionDTO.class);
		return dto;
	}

	@Override
	public List<DescriptionDTO> findAllDescriptions() {
		List<DescriptionEntity> entities = descriptionRepository.findAll();
		List<DescriptionDTO> dtos = objectMapper.mapAll(entities,DescriptionDTO.class);
		return dtos;
	}

	@Override
	public void delete() {
		descriptionRepository.deleteAll();
		
	}
	@Override
	public boolean checkIfExists() {
		boolean checker;
		if(descriptionRepository.count()==0)
			checker=false;
			else checker=true;
		return checker;
	}

}

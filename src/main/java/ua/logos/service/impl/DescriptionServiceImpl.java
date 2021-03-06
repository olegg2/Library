package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.DescriptionDTO;
import ua.logos.entity.DescriptionEntity;
import ua.logos.repository.DescriptionRepository;
import ua.logos.service.DescriptionService;
import ua.logos.utils.ObjectMapperUtils;
import ua.logos.utils.StringUtils;
@Service
public class DescriptionServiceImpl implements DescriptionService{
	@Autowired
	private DescriptionRepository descriptionRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;
	@Autowired
	private StringUtils stringUtils;
	@Override
	public void saveDescription(DescriptionDTO description) {
		String descriptionId = stringUtils.generate();
		System.out.println(descriptionId);
		
		if(!descriptionRepository.existsByDescriptionId(descriptionId)) {
			description.setDescriptionId(descriptionId);
			DescriptionEntity entity = objectMapper.map(description, DescriptionEntity.class);
			descriptionRepository.save(entity);
		}
		
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

	@Override
	public void deleteSelected(String id) {
		Long id2 = descriptionRepository.findByDescriptionId(id).getId();
		System.out.println("name of selected description : "+id+" id : " + id2);
		descriptionRepository.deleteById(id2);
		
	}

	

}

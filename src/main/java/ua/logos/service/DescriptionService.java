package ua.logos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.logos.domain.DescriptionDTO;
@Service
public interface DescriptionService {
	void saveDescription(DescriptionDTO description);
	
	DescriptionDTO findDescriptionById(Long id);
	
	List<DescriptionDTO> findAllDescriptions();
	public boolean checkIfExists();
	
	void delete();

}

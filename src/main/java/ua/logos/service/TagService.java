package ua.logos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.logos.domain.TagDTO;

@Service
public interface TagService {
	void saveTag(TagDTO tag);
	
	TagDTO findTagById(Long id);
	
	List<TagDTO> findAllTag();
	public boolean checkIfExists();
	void delete();
	void deleteSelected(String name);

}

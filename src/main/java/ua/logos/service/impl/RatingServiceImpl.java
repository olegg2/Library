package ua.logos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.RatingDTO;
import ua.logos.entity.RatingEntity;
import ua.logos.repository.RatingRepository;
import ua.logos.service.RatingService;
import ua.logos.utils.ObjectMapperUtils;
@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public void saveRating(RatingDTO rating) {
		RatingEntity entity = objectMapper.map(rating,RatingEntity.class );
		ratingRepository.save(entity);
		
	}

	@Override
	public RatingDTO findRatingById(Long id) {
		RatingEntity entity = ratingRepository.findById(id).get();
		RatingDTO dto = objectMapper.map(entity, RatingDTO.class);
		return dto;
	}

	@Override
	public List<RatingDTO> findAllRatings() {
		List<RatingEntity> entities = ratingRepository.findAll();
		List<RatingDTO> dtos = objectMapper.mapAll(entities, RatingDTO.class);
		return dtos;
	}
	@Override
	public boolean checkIfExists() {
		boolean checker;
		if(ratingRepository.count()==0)
			checker=false;
			else checker=true;
		return checker;
	}
	@Override
	public void delete() {
		ratingRepository.deleteAll();
		
	}

}

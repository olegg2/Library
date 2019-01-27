package ua.logos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.logos.domain.RatingDTO;

@Service
public interface RatingService {
	void saveRating(RatingDTO rating);
	
	RatingDTO findRatingById(Long id);
	
	List<RatingDTO> findAllRatings();
	public boolean checkIfExists();
	void delete();
	
	

}

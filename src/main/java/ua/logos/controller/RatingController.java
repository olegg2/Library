package ua.logos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.logos.domain.RatingDTO;
import ua.logos.entity.RatingEntity;
import ua.logos.service.RatingService;

@RestController
@RequestMapping("ratings")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<Void> createRating(@RequestBody RatingDTO dto){
		ratingService.saveRating(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<RatingDTO>> getAllRatings (){
		List<RatingDTO> dtos = ratingService.findAllRatings();
		return new ResponseEntity<>(dtos,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<RatingDTO> getRatingById(@PathVariable ("id") Long id){
		RatingDTO dto = ratingService.findRatingById(id);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	@GetMapping("/check")
	public ResponseEntity<Boolean> checkIfExists(){
		
		return new ResponseEntity<Boolean>(ratingService.checkIfExists(),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAll(){
		ratingService.delete();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

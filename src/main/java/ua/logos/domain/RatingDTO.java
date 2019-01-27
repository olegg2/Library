package ua.logos.domain;

import lombok.Data;

@Data
public class RatingDTO {
	private Long id;
	private int numberOfRating;
	private String titleOfRating;

}

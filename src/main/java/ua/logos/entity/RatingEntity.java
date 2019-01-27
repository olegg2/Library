package ua.logos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class RatingEntity extends BaseEntity {
	@Column(name="number_of_rating")
	private int numberOfRating;
	@Column(name="title_of_rating")
	private String titleOfRating;

}

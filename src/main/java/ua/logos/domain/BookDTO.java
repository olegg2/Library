package ua.logos.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import ua.logos.entity.AuthorEntity;
import ua.logos.entity.DescriptionEntity;
import ua.logos.entity.GenreEntity;
@Data
public class BookDTO {
	
	private Long id;
	private String bookId;
	private String title;
	private String imageUrl;
	
	
	private int year;
	private int numberOfPages;
	private DescriptionDTO description;
	private AuthorDTO author;
	private GenreDTO genre;
	private RatingDTO rating;
	private List<TagDTO> tags;

}

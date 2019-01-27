package ua.logos.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import ua.logos.entity.BookEntity;

@Data
public class AuthorDTO {
	
	private Long id;
	
	private String authorId;
	
	private String nameOfAuthor;
	
	private String email;
	
	private String authorImg;
	
	//private List<BookDTO> books;
}

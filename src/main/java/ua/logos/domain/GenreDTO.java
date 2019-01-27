package ua.logos.domain;

import java.util.List;

import lombok.Data;
import ua.logos.entity.BookEntity;
@Data
public class GenreDTO {
	private Long id;
	private String genreId;
	private String nameOfGenre;
	//private List<BookDTO> books;

}

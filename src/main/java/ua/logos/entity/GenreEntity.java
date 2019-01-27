package ua.logos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "genre_entity",indexes= @Index(columnList = "name_of_genre"))
public class GenreEntity extends BaseEntity {
	@Column(name="genre_id",unique=true)
	private String genreId;
	@Column(name="name_of_genre")
	private String nameOfGenre;
	
	@OneToMany(mappedBy ="genre")
	private List<BookEntity> books;
}

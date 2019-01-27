package ua.logos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AuthorEntity extends BaseEntity {
	@Column(name="author_id", unique=true)
	private String authorId;
	@Column(name="name_of_author")
	private String nameOfAuthor;
	@Column(name = "email")
	private String email;
	@Column(name="author_img")
	private String authorImg;
	
	
	@OneToMany(mappedBy = "author")
	private List<BookEntity> books;
	

}

package ua.logos.entity;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="book_entity",indexes = @Index(columnList = "title,book_id"))
public class BookEntity extends BaseEntity {
	@Column(name="book_id",unique=true)
	private String bookId;
	@Column(name = "title")
	private String title;
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "year")
	private int year;
	@Column(name = "number_of_pages")
	private int numberOfPages;
	//@Column(name = "description")
	//private String description;
	
	@ManyToOne 
	@JoinColumn(name="description_id")
	private DescriptionEntity description;
	
	@ManyToOne
	@JoinColumn(name= "author_id")
	private AuthorEntity author;
	
	@ManyToOne
	@JoinColumn(name="genre_id")
	private GenreEntity genre;
	
	@ManyToOne
	@JoinColumn(name="rating_id")
	private RatingEntity rating;
	
	@ManyToMany
	@JoinTable(name = "book_tag",
	joinColumns = @JoinColumn(name = "book_id"),
	inverseJoinColumns = @JoinColumn(name ="tag_id"))
	private List<TagEntity> tags;
}

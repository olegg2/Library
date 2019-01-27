package ua.logos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TagEntity extends BaseEntity {
	@Column(name="tag_id",unique=true)
	private String tagId;
	@Column(name="name_of_tag")
	private String nameOfTag;
	
	@ManyToMany(mappedBy = "tags")
	private List<BookEntity> books;

}

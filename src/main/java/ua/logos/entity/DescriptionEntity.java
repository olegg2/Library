package ua.logos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class DescriptionEntity extends BaseEntity {
	@Column(name="description_id",unique=true)
	private String descriptionId;
	@Column(name="descriptions",length=1000)
	private String descriptions;
	
	@OneToMany(mappedBy = "description")
	private List<BookEntity> books;
}

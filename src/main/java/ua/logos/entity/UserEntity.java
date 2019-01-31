package ua.logos.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity{
	@Column(unique= true,nullable=false)
	private String username;
	
	@Column(nullable= false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;
	

}

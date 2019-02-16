package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.logos.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>{
	AuthorEntity findByAuthorId(String id);
	AuthorEntity findByNameOfAuthor(String name);
	
	boolean existsByAuthorId (String authorId);
	boolean existsByEmail(String email);
	
}

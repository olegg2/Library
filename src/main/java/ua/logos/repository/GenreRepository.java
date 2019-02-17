package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.logos.entity.AuthorEntity;
import ua.logos.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Long>{

	GenreEntity findByNameOfGenre(String name);
	
	boolean existsByGenreId (String GenreId);
}

package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.logos.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Long>{

}

package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.logos.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

}

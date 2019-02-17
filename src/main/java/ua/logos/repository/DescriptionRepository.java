package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.logos.entity.DescriptionEntity;
import ua.logos.entity.GenreEntity;

public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Long> {
	DescriptionEntity findByDescriptions(String name);
	DescriptionEntity findByDescriptionId(String id);
	boolean existsByDescriptionId (String descriptionId);
}

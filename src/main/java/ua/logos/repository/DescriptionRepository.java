package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.logos.entity.DescriptionEntity;

public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Long> {

}

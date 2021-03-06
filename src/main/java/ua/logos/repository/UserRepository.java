package ua.logos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.logos.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);
	UserEntity findByPassword(String password);
	
	Boolean existsByUsername (String username);
}

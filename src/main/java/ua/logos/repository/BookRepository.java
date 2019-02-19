package ua.logos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.logos.entity.BookEntity;

public interface BookRepository extends
JpaRepository<BookEntity, Long>,
JpaSpecificationExecutor<BookEntity>{
	
	
	BookEntity findByBookId(String id);
	BookEntity findByTitle(String name);
	boolean existsByBookId(String bookId);
	
	//@Query("select b from book_entity b where b.author_id.id = : authorId")
	//List<BookEntity> findBookByAuthorId(@Param("authorId") Long id);
	//already written methods for find by something
	List<BookEntity> findByAuthorId(Long id);
	List<BookEntity> findByGenreNameOfGenre(String name);
	//already written methods for filtration
	
}

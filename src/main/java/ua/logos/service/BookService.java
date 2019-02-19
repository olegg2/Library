package ua.logos.service;

import java.util.List;

import org.hibernate.id.PersistentIdentifierGenerator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.logos.domain.BookDTO;
import ua.logos.domain.filter.SimpleFilter;
@Service
public interface BookService {
	void saveBook(BookDTO book);
	
	BookDTO findBookById(Long id);
	
	List<BookDTO> findAllBooks();
	
	void update(BookDTO dto);
	
	void delete();
	void deleteSelected(String name);
	//////////////////
	List<BookDTO> findBookByAuthorId(Long id);
	
	List<BookDTO> findBookByGenreName(String name);
	//////////////////
	List<BookDTO> findAllBooksByPages(Pageable pageble);
	//////////////////
	List<BookDTO> findAllBooksBySpecification(SimpleFilter filter);
	//////////////////
	void addImgToBook(Long id,String fileName);
	//////////////////upload image
	void uploadImage(MultipartFile file,String bookId);
	//////////////////getRandomBook
	BookDTO getRandomBook();
	//////////////////pages
	List<BookDTO> findBooksByPage(Integer num);
	//////////////////number of pages
	Integer getNumberOfPages();
}

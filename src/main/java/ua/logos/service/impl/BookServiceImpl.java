package ua.logos.service.impl;

import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Uploader;

import ua.logos.domain.BookDTO;
import ua.logos.domain.filter.SimpleFilter;
import ua.logos.entity.BookEntity;
import ua.logos.repository.BookRepository;
import ua.logos.service.BookService;
import ua.logos.service.cloudinary.CloudinaryService;
import ua.logos.utils.ObjectMapperUtils;
import ua.logos.utils.StringUtils;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;
	@Autowired
	private StringUtils stringUtils;
	@Autowired
	private CloudinaryService cloudinatyService;
	//save
	@Override
	public void saveBook(BookDTO book) {
		String bookId = stringUtils.generate();
		if(!bookRepository.existsByBookId(bookId)) {
		book.setBookId(bookId);
		BookEntity entity = objectMapper.map(book, BookEntity.class);
		bookRepository.save(entity);
		}
	}
	//findBy
	@Override
	public BookDTO findBookById(Long id) {
		BookEntity entity = bookRepository.findById(id).get();
		BookDTO dto = objectMapper.map(entity, BookDTO.class);
		return dto;
	}
	//findAll
	@Override
	public List<BookDTO> findAllBooks() {
		List<BookEntity> entities = bookRepository.findAll();
		List<BookDTO> dtos = objectMapper.mapAll(entities, BookDTO.class);
		return dtos;
	}
	//delete
	@Override
	public void delete() {
		bookRepository.deleteAll();
		
	}

	@Override
	public void update(BookDTO dto) {
		// TODO Auto-generated method stub
		
	}
	//find by author id
	@Override
	public List<BookDTO> findBookByAuthorId(Long id) {
		List<BookEntity> entities = bookRepository.findByAuthorId(id);
		List<BookDTO> dtos = objectMapper.mapAll(entities, BookDTO.class);
		return dtos;
	}
	//find by genre name
	@Override
	public List<BookDTO> findBookByGenreName(String name) {
		List<BookEntity> entities = bookRepository.findByGenreNameOfGenre(name);
		List<BookDTO> dtos = objectMapper.mapAll(entities, BookDTO.class);
		
		return dtos;
	}
	//page
	@Override
	public List<BookDTO> findAllBooksByPages(Pageable pageble) {
		Page<BookEntity> booksPage =
				bookRepository.findAll(PageRequest.of
						(pageble.getPageNumber(), pageble.getPageSize()));
		List<BookEntity> entities = booksPage.getContent();
		List<BookDTO> dtos = objectMapper.mapAll(entities, BookDTO.class);
		return dtos;
	}
	//page number
	@Override
	public Integer getNumberOfPages() {
		Integer num=null;
		int num1=bookRepository.findAll().size();
		if(num1%10>0) {
			num=num1/10+1;
		}
		if(num1%10==0) {
			num=num1/10;
		}
		System.out.println(num);
		
				
		
		return num;
		
	}
	/////////////////////////////////////////filter////////////////////////////////////////////////////////////////
	//filter
	@Override
	public List<BookDTO> findAllBooksBySpecification(SimpleFilter filter) {
	//	Specification<BookEntity> entities = getSpecification(filter);
	System.out.println(filter);
		return objectMapper.mapAll(bookRepository.findAll(getSpecification(filter)), BookDTO.class);
	}
	
	private Specification<BookEntity> getSpecification(SimpleFilter filter){
		
		return new Specification<BookEntity>() {

			@Override
			public Predicate toPredicate(
					Root<BookEntity> root,
					CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				//if(filter.getSearch().isEmpty()) {return null;}
				
				Expression<String> searchByTitleExp = root.get("title");
				System.out.println(searchByTitleExp.toString());
				Predicate searchByTitlePredicate =
						criteriaBuilder.like(searchByTitleExp, "%"+filter.getSearch()+"%");
				System.out.println(searchByTitlePredicate.toString());
				//
				Expression<String> searchByBookIdExp = root.get("bookId");
				Predicate searchByBookIdPredicate = criteriaBuilder.equal(searchByBookIdExp, filter.getSearch());
				//
				//Expression<String> searchByExp = root.get("bookId");
				//Predicate searchByBookIdPredicate = criteriaBuilder.equal(searchByBookIdExp, filter.getSearch());
				
				return criteriaBuilder.or(searchByTitlePredicate,searchByBookIdPredicate);
				//return criteriaBuilder.and(searchByTitlePredicate);
			}
		};
	}
	//////////////////////////////////////////image methods///////////////////////////////////////////////////////////////
	//take image and parse in cloudinary then get url which 
	@Override
	public void uploadImage(MultipartFile file, String bookId) {
		String imageUrl = cloudinatyService.uploadFile(file, "books");
		BookEntity entity = bookRepository.findByBookId(bookId);
		if(entity == null)
			System.out.println("book not found");
		entity.setImageUrl(imageUrl);
		bookRepository.save(entity);
		
	}
	
	//add image to book from my server
		@Override
		public void addImgToBook(Long id, String fileName) {
			BookEntity entity = bookRepository.findById(id).get();
			entity.setImageUrl(fileName);
			bookRepository.save(entity);
		}
	//random book
	@Override
	public BookDTO getRandomBook() {
		List<BookEntity>  entities = bookRepository.findAll();
		Random rand = new Random();
		
		BookEntity entity = entities.get(rand.nextInt(entities.size())+1);
		BookDTO dto = objectMapper.map(entity, BookDTO.class);
		return dto;
		
	}
	//pages
	@Override
	public List<BookDTO> findBooksByPage(Integer num) {
		PageRequest request = PageRequest.of(num,10);
		List<BookEntity> entities = bookRepository.findAll(request).getContent();
		List<BookDTO> dtos = objectMapper.mapAll(entities, BookDTO.class);
		return dtos;
	}
	
	
	
	
}

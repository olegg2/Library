package ua.logos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ua.logos.domain.BookDTO;
import ua.logos.domain.filter.SimpleFilter;
import ua.logos.entity.BookEntity;
import ua.logos.service.BookService;
import ua.logos.service.FileStorageService;
@RestController
@RequestMapping("books")
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping
	public ResponseEntity<Void> createBook(@RequestBody BookDTO dto){
		bookService.saveBook(dto);
		return new ResponseEntity<Void> (HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable ("bookId") Long id){
		BookDTO dto = bookService.findBookById(id);
		return new ResponseEntity<BookDTO> (dto,HttpStatus.ACCEPTED);
	}
	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks(){
		List<BookDTO> dtos = bookService.findAllBooks();
		return new ResponseEntity<List<BookDTO>> (dtos,HttpStatus.ACCEPTED);
	}
	//search
	@GetMapping("/sauthors/{authorId}")
	public ResponseEntity<List<BookDTO>> getBooksByAutorId(@PathVariable ("authorId") Long id,
			@RequestParam ("nameOfAuthor") String name ){
		System.out.println(name);
		
		List<BookDTO> dtos = bookService.findBookByAuthorId(id);
		return new ResponseEntity<>(dtos,HttpStatus.ACCEPTED);	
	}
	@GetMapping("sgenres/{genreName}")
	public ResponseEntity<List<BookDTO>> getBooksByGenreName(@PathVariable ("genreName") String name){
		List<BookDTO> dtos = bookService.findBookByGenreName(name);
		return new ResponseEntity<List<BookDTO>> (dtos,HttpStatus.ACCEPTED);
	}
	//pages
	@GetMapping("/pages")
	public ResponseEntity<List<BookDTO>> findAllBooksByPages
	(@PageableDefault Pageable pageble){
		List<BookDTO> dtos = bookService.findAllBooksByPages(pageble);
		return new ResponseEntity<List<BookDTO>>(dtos, HttpStatus.ACCEPTED);
	}
	//search
	@GetMapping("/search")
	public ResponseEntity<List<BookDTO>> findAllBooksBySearch(
			@RequestParam (value ="search",required = false) String search){//or SimpleFilter filter||
		
		SimpleFilter filter = new SimpleFilter();
		filter.setSearch(search);
		List<BookDTO> dtos = bookService.findAllBooksBySpecification(filter);
		//System.out.println(dtos.get(0).getTitle());
		return new ResponseEntity<List<BookDTO>>(dtos, HttpStatus.ACCEPTED);
		
	}
	//image download
	@PostMapping("/{id}/image")
	public ResponseEntity<Void> saveImg(@PathVariable ("id") Long id ,@RequestParam ("file") MultipartFile file){
		String fileName=fileStorageService.storeFile(file);
		bookService.addImgToBook(id, fileName);
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	//image upload by name of photo
	@GetMapping("/image/{fileName}")//vukachatu iz serva
	public ResponseEntity<Resource> downloadImage(
			@PathVariable("fileName") String fileName,
			HttpServletRequest request){
		Resource resource = fileStorageService.readFile(fileName);
		String contentType = null;
		try {
			contentType = request.
					getServletContext().getMimeType(resource.
							getFile().getAbsolutePath());
			
		}catch (Exception e) {e.printStackTrace();}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline: filename=\""+resource.getFilename()+"\"")
				.body(resource);
	}
	//upload image for book
	@PostMapping("/image/{bookId}")
	public ResponseEntity<Void> uploadImage(
			@PathVariable ("bookId") String bookId,
			@RequestParam ("image") MultipartFile file){
		bookService.uploadImage(file, bookId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	//get random book
	@GetMapping("/random_book")
	public ResponseEntity<BookDTO> getRandomBook(){
		BookDTO dto = bookService.getRandomBook(); 
		
		return new ResponseEntity<BookDTO>(dto,HttpStatus.OK);
		
	}
	//pages
	@GetMapping("page/{pageNumber}")
	public ResponseEntity<List<BookDTO>> findByPage(@PathVariable("pageNumber") int pageNumber){
		List<BookDTO> dtos = bookService.findBooksByPage(pageNumber);
		return new ResponseEntity<List<BookDTO>> (dtos,HttpStatus.OK);
	}
	@GetMapping("page/getPageNumber")
	public ResponseEntity<Integer> getPageNumber(){
		Integer num = bookService.getNumberOfPages();
		return new ResponseEntity<Integer> (num,HttpStatus.OK);
	}
	//delete
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAllBook(){
		bookService.delete();
		return new ResponseEntity<Void> (HttpStatus.OK);
	}

}


package ua.logos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import ua.logos.domain.AuthorDTO;
import ua.logos.service.AuthorService;

@RestController
@RequestMapping("authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@PostMapping
	public ResponseEntity<Void> createAuthor(@RequestBody AuthorDTO dto){
		
		authorService.saveAuthor(dto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{authorId}")
	public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable ("authorId") Long id){
		AuthorDTO dto = authorService.findAuthorById(id);
		return new ResponseEntity<AuthorDTO> (dto,HttpStatus.ACCEPTED);
	}
	@GetMapping
	public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
		List<AuthorDTO> dtos = authorService.findAllAuthors();
		return new ResponseEntity<>(dtos, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	private ResponseEntity<Void> deleteAll(){
		authorService.delete();
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	@GetMapping("/delete/{name}")
	private ResponseEntity<Void> deleteSelected(@PathVariable ("name") String name){
		authorService.deleteSelected(name);
		return new ResponseEntity<Void> (HttpStatus.OK);	
		
	}
	
	@PostMapping("image/{authorId}")
	public ResponseEntity<Void> uploadImage(
			@PathVariable ("authorId") String authorId,
			@RequestParam("image") MultipartFile file){
		
		authorService.uploadImg(file, authorId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED); 
	}
	@GetMapping("/check")
	public ResponseEntity<Boolean> checkIfExists(){
		
		return new ResponseEntity<Boolean>(authorService.checkIfExists(),HttpStatus.OK);
	}
	
	@GetMapping("/check-email")
	public ResponseEntity<Boolean> checkAuthorByEmail(
			@RequestParam("email") String email){
		return new ResponseEntity<Boolean>(authorService.existsByEmail(email),
				HttpStatus.OK);
	}
}

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
import org.springframework.web.bind.annotation.RestController;

import ua.logos.domain.GenreDTO;
import ua.logos.service.GenreService;

@RestController
@RequestMapping("genres")
public class GenreController {
	@Autowired
	private GenreService genreService;
	
	@PostMapping
	public ResponseEntity<Void> createGenre(@RequestBody GenreDTO dto ){
		genreService.saveGenre(dto);
		return new ResponseEntity<Void> (HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<GenreDTO>> getAllGenres(){
		List<GenreDTO> dtos = genreService.findAllGenres();
		return new ResponseEntity<List<GenreDTO>> (dtos,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<GenreDTO> getGenreById(@PathVariable ("id") Long id){
		GenreDTO dto = genreService.findGenreById(id);
		return new ResponseEntity<GenreDTO> (dto,HttpStatus.ACCEPTED);
	}
	@GetMapping("/check")
	public ResponseEntity<Boolean> checkIfExists(){
		
		return new ResponseEntity<Boolean>(genreService.checkIfExists(),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAll(){
		genreService.delete();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

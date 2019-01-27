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

import ua.logos.domain.TagDTO;
import ua.logos.service.TagService;
@RestController
@RequestMapping("tags")
public class TagController {
	@Autowired
	private TagService tagService;
	
	@PostMapping
	public ResponseEntity<Void> createTag(@RequestBody TagDTO dto){
		tagService.saveTag(dto);
		return new ResponseEntity<Void> (HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<TagDTO>> getAllTags(){
		List<TagDTO> dtos = tagService.findAllTag();
		return new ResponseEntity<List<TagDTO>>(dtos, HttpStatus.ACCEPTED);
	}
	@GetMapping("/{id}")
	private ResponseEntity<TagDTO> getTegById(@PathVariable Long id){
		TagDTO dto = tagService.findTagById(id);
		return new ResponseEntity<TagDTO>(dto, HttpStatus.ACCEPTED);
	}
	@GetMapping("/check")
	public ResponseEntity<Boolean> checkIfExists(){
		
		return new ResponseEntity<Boolean>(tagService.checkIfExists(),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAll (){
		tagService.delete();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	}

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

import ua.logos.domain.DescriptionDTO;
import ua.logos.entity.DescriptionEntity;
import ua.logos.service.DescriptionService;
import ua.logos.utils.ObjectMapperUtils;

@RestController
@RequestMapping("descriptions")
public class DescriptionController {
	@Autowired
	private DescriptionService descriptionService;
	
	@PostMapping
	public ResponseEntity<Void> createDescription(@RequestBody DescriptionDTO dto){
		descriptionService.saveDescription(dto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	@GetMapping
	public ResponseEntity<List<DescriptionDTO>> getAllDescriptions(){
		List<DescriptionDTO> dtos =descriptionService.findAllDescriptions();
	
		return new ResponseEntity<List<DescriptionDTO>> (dtos,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<DescriptionDTO> getDescription(@PathVariable ("id") Long id){
		DescriptionDTO dto = descriptionService.findDescriptionById(id);
		return new ResponseEntity<DescriptionDTO>(dto,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/check")
	public ResponseEntity<Boolean> checkIfExists(){
		
		return new ResponseEntity<Boolean>(descriptionService.checkIfExists(),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAll(){
		descriptionService.delete();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

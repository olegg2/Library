package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.logos.domain.SigninRequest;
import ua.logos.domain.SigninResponse;
import ua.logos.domain.SignupRequest;
import ua.logos.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest request){
		authService.signup(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody SigninRequest request){
		String token = authService.signin(request);
		
		return new ResponseEntity<> (new SigninResponse(token),HttpStatus.OK);
	}
	
	@GetMapping("/getCurrentUsername")
	public ResponseEntity<?> getCurrentUsername (){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("signin in authController = " + username);
		
		//System.out.println("getCurrentUsername = "+username);
		return new ResponseEntity<> (username,HttpStatus.OK);
	}

}

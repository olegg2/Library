package ua.logos.service;

import org.springframework.stereotype.Service;

import ua.logos.domain.SigninRequest;
import ua.logos.domain.SignupRequest;
@Service
public interface AuthService {

	void signup(SignupRequest request);
	
	String signin(SigninRequest request);
	
	String getUsernameByToken();
}

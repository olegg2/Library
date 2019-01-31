package ua.logos.service;

import ua.logos.domain.SigninRequest;
import ua.logos.domain.SignupRequest;

public interface AuthService {

	void signup(SignupRequest request);
	
	String signin(SigninRequest request);
}

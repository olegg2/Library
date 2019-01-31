package ua.logos.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudinary.api.exceptions.AlreadyExists;

import ua.logos.config.jwt.JwtTokenProvider;
import ua.logos.domain.SigninRequest;
import ua.logos.domain.SignupRequest;
import ua.logos.entity.RoleEntity;
import ua.logos.entity.UserEntity;
import ua.logos.exception.AlreadyExistsException;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.repository.RoleRepository;
import ua.logos.repository.UserRepository;
import ua.logos.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	

	@Override
	public void signup(SignupRequest request) {
		
		if(userRepository.existsByUsername(request.getUsername())) {
			throw new AlreadyExistsException("User with name "+ request.getUsername()+" already exists");
			
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(request.getUsername());
		userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
		
		
		RoleEntity role = roleRepository.findByName("USER").orElseThrow( 
				() -> new ResourceNotFoundException("Role not found")
				 
				);
		
		Set<RoleEntity> roles = new HashSet<>();
		roles.add(role);
		userEntity.setRoles(roles);
		
		userRepository.save(userEntity);
	}

	@Override
	public String signin(SigninRequest request) {
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								request.getUsername(), 
								request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateTolen(authentication);
		
		return token;
	}	
	
}

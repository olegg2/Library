package ua.logos.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.logos.entity.UserEntity;
import ua.logos.repository.UserRepository;
//search for user name; then create and return
//security user based on founded user entity
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User with user ["+username+"] not found"));
		//security user
		return User.builder()
				.username(entity.getUsername())
				.password(entity.getPassword())
				.authorities(getAuthority(entity))//method below
				.build()
				;
		//
	}
	//inner method which created for returning good format set of roles
	//roles are from user entity
	private Set<SimpleGrantedAuthority> getAuthority(UserEntity entity){
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		entity.getRoles().forEach(role ->{
			authorities.add( new SimpleGrantedAuthority("ROLE_"+ role.getName()));
		});
		return authorities;
	}
	
	
	
	
	
}

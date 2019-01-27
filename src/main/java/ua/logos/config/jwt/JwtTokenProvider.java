package ua.logos.config.jwt;

import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ua.logos.config.SecurityConstants;

@Component
public class JwtTokenProvider {

	public String generateTolen(Authentication authentication) {
		String authorities = authentication.getAuthorities()
				.stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		Date now = new Date();
		Date validity = new Date(now.getTime()+SecurityConstants.ACCESS_TOKEN_VALIDITY_SECONDS);
		
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(SecurityConstants.AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SIGNIN_KEY)
				.setIssuedAt(now)
				.setExpiration(validity)
				.compact()
				;
		
	};
	
	
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(SecurityConstants.SIGNIN_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return  claimsResolver.apply(claims);
		
	}
	
}

package ua.logos.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import ua.logos.config.SecurityConstants;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username = null;
		String authToken = null;
		
		String header = request.getHeader(SecurityConstants.HEADER_NAME); 
		
		if(header!= null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			authToken = header.replace(SecurityConstants.TOKEN_PREFIX, "");
			
			try {
				username= jwtTokenProvider.getUsernameFromToken(authToken);
				System.out.println("username = "+username);
				
			}catch (Exception e) {
				System.out.println("Get Username from token exception");
				e.printStackTrace();
			}
		}else {
				System.out.println("Could not find Bearer token");
			}
			if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
				System.out.println("user is found");
				if(jwtTokenProvider.validateToken(authToken,userDetails)) {
					//make token???
					UsernamePasswordAuthenticationToken authentication = 
							jwtTokenProvider.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
					
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					System.out.println("User "+ username +" added to security context");
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
				}
			}
			
			
			filterChain.doFilter(request, response);
		
	}

}

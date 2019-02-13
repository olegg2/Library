 package ua.logos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ua.logos.config.jwt.JwtAuthenticationEntryPoint;

import ua.logos.config.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//user finder
	@Autowired
	public UserDetailsService userDetailsService;
	
	@Autowired
	protected JwtAuthenticationEntryPoint unauthorizedHandler;
	
	
	@Override
	//filter
	protected void configure(HttpSecurity http) throws Exception {
		
	http.cors().and().csrf().disable();
 		

		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/auth/signin").permitAll()
		.antMatchers(HttpMethod.POST,"/auth/signup").permitAll()
		.antMatchers("/books/**").permitAll()
		.antMatchers("/sgenres/**").permitAll()
		.antMatchers("/genres/**").permitAll()
		.antMatchers("/auth/getCurrentUsername").permitAll()
		
		
		.and()
		.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/users/**").hasAnyRole("ADMIN","USER")
		.anyRequest().authenticated()
		;
		
		
		http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	//making builder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public JwtAuthenticationFilter authenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

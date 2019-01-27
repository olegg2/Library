package ua.logos.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {
	private final Random  RANDOM= new SecureRandom();
	private final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	
	public String generate() {
		return generateRandomString(10);
	}
	public String generate(int length) {
		return generateRandomString(length);
	}
	
	public String generateRandomString(int length) {
		StringBuilder builder = new StringBuilder(length);
		
		for(int i=0;i<length;i++) {
			builder.append(ALPHABET.
			charAt(RANDOM.nextInt(ALPHABET.length())));
			
		}
		return new String(builder);
	}
	
	
	
	
}

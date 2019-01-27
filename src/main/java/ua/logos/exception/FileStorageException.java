package ua.logos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270802899507371733L;

	public FileStorageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public FileStorageException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}

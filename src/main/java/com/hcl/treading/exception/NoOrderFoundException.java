package com.hcl.treading.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoOrderFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5224943416382684312L;
	
	public NoOrderFoundException(String message) {
		super(message);
	}
	
}

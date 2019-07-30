package com.hcl.treading.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class NoStockAvailableException extends Exception{

private static final long serialVersionUID = -5224942345382684312L;
	
	public NoStockAvailableException(String message) {
		super(message);
	}
}

package com.hcl.treading.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.treading.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		 List<String> details = new ArrayList<>();
	        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	            details.add(error.getDefaultMessage());
	        }
	        return new ResponseEntity<>(new ErrorResponse("Validation Failed", details,Integer.toString(HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
	    }
	 
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {   
		 List<String> details = new ArrayList<>();
	        details.add(ex.getMessage());	        
	        return new ResponseEntity<>(new ErrorResponse("Not found", details,Integer.toString(301)), HttpStatus.NOT_FOUND);
	    }
	
	@ExceptionHandler(NoOrderFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public final ResponseEntity<ErrorResponse> noOrderFoundException(NoOrderFoundException ex, WebRequest request) {   
		 List<String> details = new ArrayList<>();
	        details.add(ex.getMessage());	        
	        return new ResponseEntity<>(new ErrorResponse("Server Error", details,Integer.toString(302)), HttpStatus.NOT_FOUND);
	    }
	@ExceptionHandler(NoStockAvailableException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
		public final ResponseEntity<ErrorResponse> noStockAvailableException(NoStockAvailableException ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		return new ResponseEntity<>(new ErrorResponse("no stock",details,Integer.toString(303)),HttpStatus.NOT_FOUND);
	}
	
}

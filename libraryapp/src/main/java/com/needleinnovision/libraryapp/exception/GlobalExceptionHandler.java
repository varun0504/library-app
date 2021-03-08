package com.needleinnovision.libraryapp.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.needleinnovision.libraryapp.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static Map<Integer, HttpStatus> errorCodesmap = new HashMap<>();
	
	static {
		errorCodesmap.put(1, HttpStatus.OK);
		errorCodesmap.put(2, HttpStatus.INTERNAL_SERVER_ERROR);
		errorCodesmap.put(3, HttpStatus.UNAUTHORIZED);
		errorCodesmap.put(4, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse response = new ErrorResponse("data validation error", 
				ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AppException.class)
	  public final ResponseEntity<Object> handleUserNotFoundException(AppException ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse("business error",
				ex.getErrorDetails().getMessage());
	    return new ResponseEntity<>(response, errorCodesmap.get(ex.getErrorDetails().getErrorTypeCode()));
	  }
	
	 @ExceptionHandler(Exception.class)
	  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		 ErrorResponse response = new ErrorResponse("unexpected error",
					ex.getMessage());
		 return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}

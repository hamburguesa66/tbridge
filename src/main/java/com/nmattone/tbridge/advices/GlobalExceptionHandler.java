package com.nmattone.tbridge.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nmattone.tbridge.exceptions.APIError;
import com.nmattone.tbridge.exceptions.InvalidTaskTypeError;
import com.nmattone.tbridge.exceptions.TrelloMappingError;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(APIError.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String apiErrorHandler(APIError error) {
		log.error("Error con API de Trello", error);
		return error.getMessage();
	}
	
	@ExceptionHandler(InvalidTaskTypeError.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String invalidTaskTypeErrorHandler(InvalidTaskTypeError error) {
		return error.getMessage();
	}
	
	@ExceptionHandler(TrelloMappingError.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String trelloMappingErrorHandler(TrelloMappingError error) {
		return error.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void defaultErrorHandler(Exception error) {
		log.error("Error no esperado", error);
	}
	
}

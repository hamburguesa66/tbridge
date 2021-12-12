package com.nmattone.tbridge.exceptions;

/**
 *	Error al comunicarse con la API de Trello.
 */
@SuppressWarnings("serial")
public class APIError extends Exception {

	public APIError(String message, Throwable cause) {
		super(message, cause);
	}

	public APIError(String message) {
		super(message);
	}

}

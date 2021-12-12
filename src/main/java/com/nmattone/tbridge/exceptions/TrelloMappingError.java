package com.nmattone.tbridge.exceptions;

/**
 *	Error al convertir una tarea a una entidad de Trello.
 */
@SuppressWarnings("serial")
public class TrelloMappingError extends Exception {

	public TrelloMappingError(String message) {
		super(message);
	}
	
}

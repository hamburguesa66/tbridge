package com.nmattone.tbridge.exceptions;


/**
 *	Error al crear una tarea cuyo tipo no está registrado.
 */
@SuppressWarnings("serial")
public class InvalidTaskTypeError extends Exception {

	public InvalidTaskTypeError(String message) {
		super(message);
	}
}

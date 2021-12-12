package com.nmattone.tbridge.services;

import com.nmattone.tbridge.dto.TaskParametersTO;
import com.nmattone.tbridge.exceptions.InvalidTaskTypeError;
import com.nmattone.tbridge.model.AbstractTask;

/**
 *	Servicio para la creación de tareas.
 */
public interface TaskFactory {

	/**
	 *	Crea una instancia de una tarea de acuerdo a los parámetros recibidos.
	 */
	public AbstractTask createNewTask(TaskParametersTO parameters) throws InvalidTaskTypeError;
	
}

package com.nmattone.tbridge.services.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.nmattone.tbridge.dto.TaskParametersTO;
import com.nmattone.tbridge.exceptions.InvalidTaskTypeError;
import com.nmattone.tbridge.model.AbstractTask;
import com.nmattone.tbridge.model.Bug;
import com.nmattone.tbridge.model.Issue;
import com.nmattone.tbridge.model.Task;
import com.nmattone.tbridge.services.TaskFactory;

/**
 *	Implementación simple de un creador de tareas. 
 */
@Service
public class SimpleTaskFactory implements TaskFactory {

	@Override
	public AbstractTask createNewTask(TaskParametersTO parameters) throws InvalidTaskTypeError {
		switch (parameters.getType()) {
		case "issue": 
			return this.createIssue(parameters.getTitle(), parameters.getDescription());
		case "bug":
			return this.createBug(parameters.getDescription());
		case "task":
			return this.createTask(parameters.getTitle(), parameters.getCategory());
		default:
			throw new InvalidTaskTypeError("El tipo de tarea ingresado ('"+parameters.getType()+"') no existe.");
		}
	}
	
	/*
	 *	Crea una instancia de un 'Issue', a partir de un título y una descripción.
	 *	Este tipo de tarea no tiene categoría. 
	 */
	private AbstractTask createIssue(String title, String description) {
		Issue issue = new Issue();
		issue.setTitle(title); // TODO: Verificar que no sea nulo, vacío, longitud, etc.
		issue.setDescription(description); // TODO: Verificar que no sea nulo, vacío, longitud, etc.
		issue.setCategory(null);
		return issue;
	}
	
	/*
	 *	Crea una instancia de un 'Bug', a partir de una descripción.
	 *	El título de la tarea se crea aletoriamente con el siguiente formato "bug-{randomWord}-{randomNumber}".
	 *	Este tipo de tarea no tiene categoría.
	 */
	private AbstractTask createBug(String description) {
		Bug bug = new Bug();
		bug.setTitle("bug"+"-"+this.generateRandomWord()+"-"+this.generateRandomNumber());
		bug.setDescription(description); // TODO: Verificar que no sea nulo, vacío, longitud, etc.
		bug.setCategory(null);
		return bug;
	}
	
	/*
	 *	Crea una instancia de un 'Task', a partir de un título y una categoría.
	 *	Este tipo de tarea no tiene descripción. 
	 */
	private AbstractTask createTask(String title, String category) {
		Task task = new Task();
		task.setTitle(title); // TODO: Verificar que no sea nulo, vacío, longitud, etc.
		task.setDescription(null);
		task.setCategory(category); // TODO: Verificar que no sea nulo, vacío, longitud, ¿qué exista en Trello?, etc.
		return task;
	}
	
	/*
	 *	Retorna un número aleatorio entre 0 y 500.
	 */
	private Integer generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(500);
	}
	
	/*
	 *	Retorna un string de longitud 10, formado sólo por caracteres [a,z]. 
	 */
	private String generateRandomWord() {
		int wordLength = 10;
	    Random r = new Random();
	    StringBuilder sb = new StringBuilder(wordLength);
	    for(int i = 0; i < wordLength; i++) {
	        char tmp = (char) ('a' + r.nextInt('z' - 'a'));
	        sb.append(tmp);
	    }
	    return sb.toString();
	}

}

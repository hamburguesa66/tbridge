package com.nmattone.tbridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nmattone.tbridge.dto.TaskParametersTO;
import com.nmattone.tbridge.exceptions.APIError;
import com.nmattone.tbridge.exceptions.InvalidTaskTypeError;
import com.nmattone.tbridge.exceptions.TrelloMappingError;
import com.nmattone.tbridge.model.AbstractTask;
import com.nmattone.tbridge.services.TaskFactory;
import com.nmattone.tbridge.services.TrelloAPIFacade;

@RestController
public class TaskController {

	@Value("${trello.board.id}")
	private String boardId;
	
	@Autowired
	private TrelloAPIFacade api;
	
	@Autowired
	private TaskFactory factory;
	
	@PostMapping
	public void createTask(@RequestBody TaskParametersTO parameters) throws InvalidTaskTypeError, TrelloMappingError, APIError {
		AbstractTask task = this.factory.createNewTask(parameters);
		this.api.createNewCard(task.mapToTrelloCard(this.api.getBoard(this.boardId), this.api));
	}
	
}

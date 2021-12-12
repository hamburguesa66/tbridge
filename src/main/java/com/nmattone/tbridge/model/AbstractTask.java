package com.nmattone.tbridge.model;

import java.util.ArrayList;
import java.util.List;

import com.nmattone.tbridge.exceptions.APIError;
import com.nmattone.tbridge.exceptions.TrelloMappingError;
import com.nmattone.tbridge.model.trello.TrelloBoard;
import com.nmattone.tbridge.model.trello.TrelloCard;
import com.nmattone.tbridge.model.trello.TrelloLabel;
import com.nmattone.tbridge.model.trello.TrelloList;
import com.nmattone.tbridge.model.trello.TrelloMember;
import com.nmattone.tbridge.services.TrelloAPIFacade;

public abstract class AbstractTask {

	private String title;
	private String description;
	private String category;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * @throws APIError 
	 * @throws TrelloMappingError 
	 *	
	 */
	public TrelloCard mapToTrelloCard(TrelloBoard board, TrelloAPIFacade api) throws TrelloMappingError, APIError {
		// Creo la 'Card' de Trello.
		TrelloCard card = new TrelloCard();
		card.setId(null);
		card.setName(this.getTitle());
		card.setDescription(this.getDescription());
		card.setList(this.getTrelloList(board, api));
		card.setMembers(this.getTrelloMemberList(board, api));
		card.setLabels(this.getTrelloLabelList(board, api));
		// Retorno la 'Card' creada.
		return card;
	}
	
	/**
	 * 
	 */
	protected TrelloList getTrelloList(TrelloBoard board, TrelloAPIFacade api) throws TrelloMappingError, APIError {
		return api.getListsOnBoard(board).stream()
				.filter(l -> l.getName().equals("To Do"))
				.findFirst()
				.orElseThrow(() -> new TrelloMappingError("No se ha encontrado la lista 'To Do'."));
	}
	
	/**
	 * 
	 */
	protected List<TrelloMember> getTrelloMemberList(TrelloBoard board, TrelloAPIFacade api) throws TrelloMappingError, APIError {
		return new ArrayList<>();
	}
	
	/**
	 *	
	 */
	protected List<TrelloLabel> getTrelloLabelList(TrelloBoard board, TrelloAPIFacade api) throws TrelloMappingError, APIError {
		return new ArrayList<>();
	}
	
}

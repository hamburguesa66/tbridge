package com.nmattone.tbridge.services;

import java.util.List;

import com.nmattone.tbridge.exceptions.APIError;
import com.nmattone.tbridge.model.trello.TrelloBoard;
import com.nmattone.tbridge.model.trello.TrelloCard;
import com.nmattone.tbridge.model.trello.TrelloLabel;
import com.nmattone.tbridge.model.trello.TrelloList;
import com.nmattone.tbridge.model.trello.TrelloMember;

/**
 *	Servicio "fachada" para interactuar con la API de Trello.
 */
public interface TrelloAPIFacade {

	/**
	 *	Obtiene el tablero de Trello correspondiente al ID recibido como parámetro.
	 */
	public TrelloBoard getBoard(String boardId) throws APIError;
	
	/**
	 *	Obtiene las listas correspondientes al tablero recibido como parámetro.
	 */
	public List<TrelloList> getListsOnBoard(TrelloBoard board) throws APIError;
	
	/**
	 *	Obtiene los miembros (usuarios) correspondientes al tablero recibido como parámetro.
	 */
	public List<TrelloMember> getMembersOnBoard(TrelloBoard board) throws APIError;
	
	/**
	 *	Obtiene las etiquetas (labels) correspondientes al tablero recibido como parámetro.
	 */
	public List<TrelloLabel> getLabelsOnBoard(TrelloBoard board) throws APIError;
	
	/**
	 *	Crea una nueva tarjeta en Trello.
	 */
	public String createNewCard(TrelloCard card) throws APIError;
	
	
}

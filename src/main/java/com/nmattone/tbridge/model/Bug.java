package com.nmattone.tbridge.model;

import java.util.Arrays;
import java.util.List;

import com.nmattone.tbridge.exceptions.APIError;
import com.nmattone.tbridge.exceptions.TrelloMappingError;
import com.nmattone.tbridge.model.trello.TrelloBoard;
import com.nmattone.tbridge.model.trello.TrelloLabel;
import com.nmattone.tbridge.model.trello.TrelloMember;
import com.nmattone.tbridge.services.TrelloAPIFacade;

public class Bug extends AbstractTask {

	private static String BUG_LABEL_NAME = "Bug";
	
	@Override
	protected List<TrelloMember> getTrelloMemberList(TrelloBoard board, TrelloAPIFacade api) throws TrelloMappingError, APIError {
		// Obtengo uno de los miembros del tablero (al "azar").
		TrelloMember randomMember = api.getMembersOnBoard(board).stream()
				.findFirst()
				.orElseThrow(() -> new TrelloMappingError("No se han encontrado miembros en Trello para asignar."));
		// Retorno una lista con el miembro como único elemento.
		return Arrays.asList(randomMember);
	}

	@Override
	protected List<TrelloLabel> getTrelloLabelList(TrelloBoard board, TrelloAPIFacade api) throws TrelloMappingError, APIError {
		// Obtengo la etiqueta correspondiente a mi categoría.
		TrelloLabel label =  api.getLabelsOnBoard(board).stream()
				.filter(l -> l.getName().equals(BUG_LABEL_NAME))
				.findFirst()
				.orElseThrow(() -> new TrelloMappingError("Etiqueta '"+BUG_LABEL_NAME+"' no encontrada en Trello."));
		// Retorno una lista con la etiqueta como único elemento.
		return Arrays.asList(label);
	}

}

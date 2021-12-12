package com.nmattone.tbridge.model;

import java.util.Arrays;
import java.util.List;

import com.nmattone.tbridge.exceptions.APIError;
import com.nmattone.tbridge.exceptions.TrelloMappingError;
import com.nmattone.tbridge.model.trello.TrelloBoard;
import com.nmattone.tbridge.model.trello.TrelloLabel;
import com.nmattone.tbridge.services.TrelloAPIFacade;

public class Task extends AbstractTask {

	@Override
	protected List<TrelloLabel> getTrelloLabelList(TrelloBoard board, TrelloAPIFacade api) throws TrelloMappingError, APIError {
		// Obtengo la etiqueta correspondiente a mi categoría.
		TrelloLabel label =  api.getLabelsOnBoard(board).stream()
				.filter(l -> l.getName().equals(this.getCategory()))
				.findFirst()
				.orElseThrow(() -> new TrelloMappingError("Etiqueta '"+this.getCategory()+"' no encontrada en Trello."));
		// Retorno una lista con la etiqueta como único elemento.
		return Arrays.asList(label);
	}
	
}

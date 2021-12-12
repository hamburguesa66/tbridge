package com.nmattone.tbridge.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.nmattone.tbridge.exceptions.APIError;
import com.nmattone.tbridge.model.trello.TrelloBoard;
import com.nmattone.tbridge.model.trello.TrelloCard;
import com.nmattone.tbridge.model.trello.TrelloLabel;
import com.nmattone.tbridge.model.trello.TrelloList;
import com.nmattone.tbridge.model.trello.TrelloMember;
import com.nmattone.tbridge.services.TrelloAPIFacade;

/**
 *	Servicio "fachada" para interactuar con la API de Trello.
 *	Utiliza la librería "Unirest" para realizar las peticiones HTTP.
 */
@Service
public class MyTrelloFacadeImpl implements TrelloAPIFacade {

	@Value("${trello.api.url}")
	private String apiBaseUrl;
	
	@Value("${trello.api.key}")
	private String apiKey;
	
	@Value("${trello.api.token}")
	private String apiToken;
	
	@Override
	public TrelloBoard getBoard(String boardId) throws APIError {
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.get(apiBaseUrl+"1/boards/"+boardId)
					.queryString("key", apiKey)
					.queryString("token", apiToken)
					.asJson();
			
			if(response.getStatus() == 200) {
				TrelloBoard board = new TrelloBoard();
				board.setId((String) response.getBody().getObject().get("id"));
				board.setName((String) response.getBody().getObject().get("name"));
				board.setUrl((String) response.getBody().getObject().get("url"));
				return board;
			} else {
				throw new APIError("Tablero no encontrado.");
			}
		} catch (UnirestException e) {
			throw new APIError("Error en la comunicacion con la API", e);
		} catch (JSONException e) {
			throw new APIError("Error al parsear respuesta", e);
		}
	}

	@Override
	public List<TrelloList> getListsOnBoard(TrelloBoard board) throws APIError {
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.get(apiBaseUrl+"1/boards/"+board.getId()+"/lists")
					.queryString("key", apiKey)
					.queryString("token", apiToken)
					.asJson();
			
			List<TrelloList> lists = new ArrayList<>();
			JSONArray arrayData = response.getBody().getArray();
			Iterator<Object> it = arrayData.iterator();
			while(it.hasNext()){
				JSONObject data = (JSONObject) it.next();
				TrelloList list = new TrelloList();
				list.setId((String) data.get("id"));
				list.setName((String) data.get("name"));
				lists.add(list);
			}
			return lists;
		} catch (UnirestException e) {
			throw new APIError("Error en la comunicacion con la API", e);
		} catch (JSONException e) {
			throw new APIError("Error al parsear respuesta", e);
		}
	}

	@Override
	public List<TrelloMember> getMembersOnBoard(TrelloBoard board) throws APIError {
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.get(apiBaseUrl+"1/boards/"+board.getId()+"/members")
					.queryString("key", apiKey)
					.queryString("token", apiToken)
					.asJson();
			
			List<TrelloMember> members = new ArrayList<>();
			JSONArray arrayData = response.getBody().getArray();
			Iterator<Object> it = arrayData.iterator();
			while(it.hasNext()){
				JSONObject data = (JSONObject) it.next();
				TrelloMember member = new TrelloMember();
				member.setId((String) data.get("id"));
				member.setUsername((String) data.get("username"));
				member.setFullname((String) data.get("fullName"));
				members.add(member);
			}
			return members;
		} catch (UnirestException e) {
			throw new APIError("Error en la comunicacion con la API", e);
		} catch (JSONException e) {
			throw new APIError("Error al parsear respuesta", e);
		}
	}

	@Override
	public List<TrelloLabel> getLabelsOnBoard(TrelloBoard board) throws APIError {
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.get(apiBaseUrl+"1/boards/"+board.getId()+"/labels")
					.queryString("key", apiKey)
					.queryString("token", apiToken)
					.asJson();
			
			List<TrelloLabel> labels = new ArrayList<>();
			JSONArray arrayData = response.getBody().getArray();
			Iterator<Object> it = arrayData.iterator();
			while(it.hasNext()){
				JSONObject data = (JSONObject) it.next();
				TrelloLabel label = new TrelloLabel();
				label.setId((String) data.get("id"));
				label.setName((String) data.get("name"));
				labels.add(label);
			}
			return labels;
		} catch (UnirestException e) {
			throw new APIError("Error en la comunicacion con la API", e);
		} catch (JSONException e) {
			throw new APIError("Error al parsear respuesta", e);
		}
	}

	@Override
	public String createNewCard(TrelloCard card) throws APIError {
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.post(apiBaseUrl+"1/cards")
					.queryString("key", apiKey)
					.queryString("token", apiToken)
					.queryString("idList", card.getList().getId())
					.queryString("name", card.getName())
					.queryString("desc", card.getDescription())
					.queryString("idMembers", card.getMembers().stream().map(TrelloMember::getId).collect(Collectors.joining(",")))
					.queryString("idLabels", card.getLabels().stream().map(TrelloLabel::getId).collect(Collectors.joining(",")))
					.asJson();
			
			if(response.getStatus() == 200) {
				return (String) response.getBody().getObject().get("id");
			} else {
				throw new APIError("Error (no esperado) al crear la card.");
			}
		} catch (UnirestException e) {
			throw new APIError("Error en la comunicacion con la API", e);
		} catch (JSONException e) {
			throw new APIError("Error al parsear respuesta", e);
		}
	}

}

package com.nmattone.tbridge.model.trello;

import java.util.List;

public class TrelloCard {

	private String id;
	private String name;
	private String description;
	private TrelloList list;
	private List<TrelloMember> members;
	private List<TrelloLabel> labels;
	
	public TrelloCard() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TrelloList getList() {
		return list;
	}

	public void setList(TrelloList list) {
		this.list = list;
	}

	public List<TrelloMember> getMembers() {
		return members;
	}

	public void setMembers(List<TrelloMember> members) {
		this.members = members;
	}

	public List<TrelloLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<TrelloLabel> labels) {
		this.labels = labels;
	}
	
}

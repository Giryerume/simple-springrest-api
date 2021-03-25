package com.giryerume.osworks.api.model;

import java.time.OffsetDateTime;

public class CommentaryModel {
	
	private Long id;
	private String description;
	private OffsetDateTime dateSend;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OffsetDateTime getDateSend() {
		return dateSend;
	}
	public void setDateSend(OffsetDateTime dateSend) {
		this.dateSend = dateSend;
	}
}

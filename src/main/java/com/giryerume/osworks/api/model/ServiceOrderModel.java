package com.giryerume.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.giryerume.osworks.domain.model.ServiceOrderStatus;

public class ServiceOrderModel {
	
	private Long id;
	private ClientBriefModel client;
	private String description;
	private BigDecimal price;
	private ServiceOrderStatus status;
	private OffsetDateTime dateOpening;
	private OffsetDateTime dateTermination;
	
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ServiceOrderStatus getStatus() {
		return status;
	}
	public void setStatus(ServiceOrderStatus status) {
		this.status = status;
	}
	public OffsetDateTime getDateOpening() {
		return dateOpening;
	}
	public void setDateOpening(OffsetDateTime dateOpening) {
		this.dateOpening = dateOpening;
	}
	public OffsetDateTime getDateTermination() {
		return dateTermination;
	}
	public void setDateTermination(OffsetDateTime dateTermination) {
		this.dateTermination = dateTermination;
	}
	public ClientBriefModel getClient() {
		return client;
	}
	public void setClient(ClientBriefModel client) {
		this.client = client;
	}
	
}

package com.giryerume.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giryerume.osworks.domain.exception.DomainException;
import com.giryerume.osworks.domain.exception.NotFoundEntityException;
import com.giryerume.osworks.domain.model.Client;
import com.giryerume.osworks.domain.model.Commentary;
import com.giryerume.osworks.domain.model.ServiceOrder;
import com.giryerume.osworks.domain.model.ServiceOrderStatus;
import com.giryerume.osworks.domain.repository.ClientRepository;
import com.giryerume.osworks.domain.repository.CommentaryRepository;
import com.giryerume.osworks.domain.repository.ServiceOrderRepository;

@Service
public class ServiceOrderManagementService {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommentaryRepository commentaryRepository;
	
	public ServiceOrder create(ServiceOrder serviceOrder) {
		Client client = clientRepository.findById(serviceOrder.getClient()
				.getId()).orElseThrow(() -> new DomainException("Client not found"));  
		serviceOrder.setClient(client);
		serviceOrder.setStatus(ServiceOrderStatus.OPENED);
		serviceOrder.setDateOpening(OffsetDateTime.now());
		
		return serviceOrderRepository.save(serviceOrder);
	}
	
	public void terminateOrder(Long serviceOrderId) {
		ServiceOrder serviceOrder = search(serviceOrderId);
		serviceOrder.terminate();
		serviceOrderRepository.save(serviceOrder);
	}
	
	public Commentary comment(Long serviceOrderId, String description) {
		ServiceOrder order = search(serviceOrderId);
		Commentary commentay = new Commentary();
		commentay.setDateSend(OffsetDateTime.now());
		commentay.setDescription(description);
		commentay.setServiceOrder(order);
		return commentaryRepository.save(commentay);
	}

	private ServiceOrder search(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new NotFoundEntityException("Service order not found"));
	}
}

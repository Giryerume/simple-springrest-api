package com.giryerume.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giryerume.osworks.domain.exception.DomainException;
import com.giryerume.osworks.domain.model.Client;
import com.giryerume.osworks.domain.repository.ClientRepository;

@Service
public class ClientRegisterService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client include(Client client) {
		Client existentClient = clientRepository.findByEmail(client.getEmail());
		if (existentClient != null && !existentClient.equals(client)) {
			throw new DomainException("Email already used.");
		}
		return clientRepository.save(client);
	}
	
	public void exclude(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}

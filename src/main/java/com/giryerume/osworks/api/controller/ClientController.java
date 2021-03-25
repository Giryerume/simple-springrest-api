package com.giryerume.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.giryerume.osworks.domain.model.Client;
import com.giryerume.osworks.domain.repository.ClientRepository;
import com.giryerume.osworks.domain.service.ClientRegisterService;

@RestController
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientRegisterService registerService;
	
	@GetMapping
	public List<Client> getClients() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> getClient(@PathVariable Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		
		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Client addClient(@Valid @RequestBody Client client){
		return registerService.include(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> setClient(@Valid @PathVariable Long clientId, @RequestBody Client client) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		client.setId(clientId);
		client = registerService.include(client);
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		registerService.exclude(clientId);
		return ResponseEntity.noContent().build();
	}
}

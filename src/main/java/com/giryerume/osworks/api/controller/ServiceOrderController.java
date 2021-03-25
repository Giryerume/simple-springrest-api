package com.giryerume.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.giryerume.osworks.api.model.ServiceOrderInput;
import com.giryerume.osworks.api.model.ServiceOrderModel;
import com.giryerume.osworks.domain.model.ServiceOrder;
import com.giryerume.osworks.domain.repository.ServiceOrderRepository;
import com.giryerume.osworks.domain.service.ServiceOrderManagementService;

@RestController
@RequestMapping("service-orders")
public class ServiceOrderController {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ServiceOrderManagementService managementService;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@GetMapping
	public List<ServiceOrderModel> getServiceOrders() {
		return toModelCollection(serviceOrderRepository.findAll());
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<ServiceOrderModel> getServiceOrder(@PathVariable Long orderId) {
		Optional<ServiceOrder> order = serviceOrderRepository.findById(orderId);
		
		if (order.isPresent()) {
			ServiceOrderModel model = toModel(order.get());
			return ResponseEntity.ok(model);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ServiceOrderModel addServiceOrder(@Valid @RequestBody ServiceOrderInput orderInput){
		ServiceOrder order = toEntity(orderInput); 
		return toModel(managementService.create(order));
	}
	
	@PutMapping("/{orderId}/terminate")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void terminateServiceOrder(@PathVariable Long orderId) {
		managementService.terminateOrder(orderId);
	}
	
	private ServiceOrderModel toModel(ServiceOrder serviceOrder) {
		return modelMapper.map(serviceOrder, ServiceOrderModel.class);
	}
	
	private List<ServiceOrderModel> toModelCollection(List<ServiceOrder> serviceOrders){
		return serviceOrders.stream()
				.map(serviceOrder -> toModel(serviceOrder))
				.collect(Collectors.toList());
	}
	
	private ServiceOrder toEntity(ServiceOrderInput input) {
		return modelMapper.map(input, ServiceOrder.class);
	}
	
}

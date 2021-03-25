package com.giryerume.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.giryerume.osworks.api.model.CommentaryInput;
import com.giryerume.osworks.api.model.CommentaryModel;
import com.giryerume.osworks.domain.exception.NotFoundEntityException;
import com.giryerume.osworks.domain.model.Commentary;
import com.giryerume.osworks.domain.model.ServiceOrder;
import com.giryerume.osworks.domain.repository.ServiceOrderRepository;
import com.giryerume.osworks.domain.service.ServiceOrderManagementService;

@RestController
@RequestMapping("service-orders/{orderId}/commentaries")
public class CommentayController {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ServiceOrderManagementService managementService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CommentaryModel> getCommentaries(@PathVariable Long orderId) {
		ServiceOrder order = serviceOrderRepository.findById(orderId).orElseThrow(() -> new NotFoundEntityException("Service Order not found"));
		return toModelCollection(order.getComments());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CommentaryModel addCommentary(@PathVariable Long orderId, @Valid @RequestBody CommentaryInput commentaryInput){
		Commentary commentary = managementService.comment(orderId, commentaryInput.getDescription()) ; 
		return toModel(commentary);
	}
	
	private CommentaryModel toModel(Commentary commentary) {
		return modelMapper.map(commentary, CommentaryModel.class);
	}
	
	private List<CommentaryModel> toModelCollection(List<Commentary> commentaries){
		return commentaries.stream()
				.map(commentary -> toModel(commentary))
				.collect(Collectors.toList());
	}
//	
//	private Commentary toEntity(CommentaryInput input) {
//		return modelMapper.map(input, Commentary.class);
//	}
	
}

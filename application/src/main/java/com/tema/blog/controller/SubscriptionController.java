package com.tema.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tema.blog.dto.SubscriberDto;
import com.tema.blog.facade.SubscriptionFacade;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionFacade subscriptionFacade;
	
	@PostMapping("/")
	public void subscribe(@Valid @RequestBody SubscriberDto subscriptionDto) {
		subscriptionFacade.addSubscriber(subscriptionDto);
	}
	
}

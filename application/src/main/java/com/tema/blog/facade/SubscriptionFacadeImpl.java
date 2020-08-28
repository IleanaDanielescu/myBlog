package com.tema.blog.facade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tema.blog.entity.Subscriber;
import com.tema.blog.exception.BusinessException;
import com.tema.blog.exception.ErrorCode;
import com.tema.blog.facade.config.Facade;
import com.tema.blog.facade.dto.SubscriberDto;
import com.tema.blog.facade.mapper.SubscriberMapper;
import com.tema.blog.repository.SubscriberRepository;

@Facade
public class SubscriptionFacadeImpl implements SubscriptionFacade {
	
	@Autowired
	private SubscriberRepository subscriberRepo;
	
	@Autowired
	private SubscriberMapper subscriberMapper;
	
	public void addSubscriber(SubscriberDto subscriptionDto) {
		checkSubscriberAlreadyExists(subscriptionDto);
		Subscriber subscriber = subscriberMapper.toEntity(subscriptionDto); 
		subscriberRepo.save(subscriber);
	}

	private void checkSubscriberAlreadyExists(SubscriberDto subscriptionDto) {
		Optional<Subscriber> subscriberOpt = subscriberRepo.findByEmail(subscriptionDto.getEmail());
		if(subscriberOpt.isPresent()) {
			throw new BusinessException("error.subscriber.already.exists", ErrorCode.EMAIL_ALREADY_EXISTS);
		}
	}
	
}

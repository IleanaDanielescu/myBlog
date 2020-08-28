package com.tema.blog.facade.mapper;

import org.springframework.stereotype.Service;

import com.tema.blog.dto.SubscriberDto;
import com.tema.blog.entity.Subscriber;

@Service
public class SubscriberMapper {

	public SubscriberDto toDto(Subscriber entity) {
		SubscriberDto dto = new SubscriberDto();
		dto.setEmail(entity.getEmail());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setSendEmail(entity.isSendEmail());
		dto.setSendSMS(entity.isSendSMS());
		return dto;
	}
	
	public Subscriber toEntity(SubscriberDto dto) {
		Subscriber entity = new Subscriber();
		entity.setEmail(dto.getEmail());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setSendEmail(dto.isSendEmail());
		entity.setSendSMS(dto.isSendSMS());
		return entity;
	}
	
}

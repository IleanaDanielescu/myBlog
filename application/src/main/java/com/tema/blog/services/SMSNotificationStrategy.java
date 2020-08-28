package com.tema.blog.services;

import com.tema.blog.bo.ArticleInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SMSNotificationStrategy implements NotificationStrategy{

	private String phoneNumber;
	
	public SMSNotificationStrategy(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	public void notify(ArticleInfo articleNotification) {
		log.info("Article title: {}", articleNotification.getTitle());
		log.info("Article content: {}", articleNotification.getContent());
		log.info("Send SMS to: {}", phoneNumber);
	}

	
}

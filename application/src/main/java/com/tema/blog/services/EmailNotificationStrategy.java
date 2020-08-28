package com.tema.blog.services;

import com.tema.blog.bo.ArticleInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailNotificationStrategy implements NotificationStrategy{

	private String email;
	
	public EmailNotificationStrategy(String email) {
		super();
		this.email = email;
	}

	@Override
	public void notify(ArticleInfo articleNotification) {
		log.info("Article title: {}", articleNotification.getTitle());
		log.info("Article content: {}", articleNotification.getContent());
		log.info("Send email to: {}", email);
	}

}

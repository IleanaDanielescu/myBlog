package com.tema.blog.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.tema.blog.event.ArticleEvent;
import com.tema.blog.services.NotificationService;

@Component
public class ArticleEventListener implements ApplicationListener<ArticleEvent>{

	@Autowired
	private NotificationService notificationService;
	
	@Async
	public void onApplicationEvent(ArticleEvent event) {
		notificationService.sendNotifications(event.getEventType(),event.getArticle());
	}
	
}

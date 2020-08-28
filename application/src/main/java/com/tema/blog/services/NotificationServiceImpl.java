package com.tema.blog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tema.blog.bo.ArticleInfo;
import com.tema.blog.entity.Subscriber;
import com.tema.blog.event.ArticleEventType;
import com.tema.blog.facade.dto.ArticleDto;
import com.tema.blog.repository.SubscriberRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	public void sendNotifications(ArticleEventType articleEventType, ArticleDto articleDto) {
		ArticleInfo articleInfo = mapToArticleInfo(articleEventType, articleDto);
		List<Subscriber> subscribers = subscriberRepository.findAll();
		subscribers.stream()
			.map(this::getNotificationStrategies)
			.flatMap(x -> x.stream())
			.forEach(e -> e.notify(articleInfo));
	}
	
	private ArticleInfo mapToArticleInfo(ArticleEventType articleEventType, ArticleDto articleDto) {
		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setTitle(articleDto.getTitle());
		articleInfo.setContent(articleDto.getContent());
		articleInfo.setCreationDate(articleDto.getCreationDate());
		articleInfo.setArticleEventType(articleEventType);
		return articleInfo;
	}
	
	private List<NotificationStrategy> getNotificationStrategies(Subscriber subscriber) {
		List<NotificationStrategy> notificationStrategies = new ArrayList<>();
		if(subscriber.isSendEmail()) {
			notificationStrategies.add(new EmailNotificationStrategy(subscriber.getEmail()));
		}
		if(subscriber.isSendSMS()) {
			notificationStrategies.add(new SMSNotificationStrategy(subscriber.getPhoneNumber()));
		}
		
		return notificationStrategies;
	}
}

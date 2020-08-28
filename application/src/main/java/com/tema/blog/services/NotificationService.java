package com.tema.blog.services;

import com.tema.blog.dto.ArticleDto;
import com.tema.blog.event.ArticleEventType;

public interface NotificationService {

	void sendNotifications(ArticleEventType articleEventType, ArticleDto articleDto);

}
package com.tema.blog.services;

import com.tema.blog.event.ArticleEventType;
import com.tema.blog.facade.dto.ArticleDto;

public interface NotificationService {

	void sendNotifications(ArticleEventType articleEventType, ArticleDto articleDto);

}
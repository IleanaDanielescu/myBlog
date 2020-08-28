package com.tema.blog.event;

import org.springframework.context.ApplicationEvent;

import com.tema.blog.facade.dto.ArticleDto;

import lombok.Getter;

@Getter
public class ArticleEvent extends ApplicationEvent{

	private ArticleDto article;
	
	private ArticleEventType eventType;
	
	public ArticleEvent(Object source, ArticleEventType eventType, ArticleDto article) {
		super(source);
		this.article = article;
		this.eventType = eventType;
	}

	private static final long serialVersionUID = 1L;

	
}

package com.tema.blog.bo;

import java.util.Date;

import com.tema.blog.event.ArticleEventType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleInfo {

	private String title;
	
	private String content;
	
	private Date creationDate;
	
	private ArticleEventType articleEventType;
	
}

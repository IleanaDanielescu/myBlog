package com.tema.blog.facade.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ArticleDto extends BasicArticleDto{

	private Long id;

	private Date creationDate;
}

package com.tema.blog.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticlesPageDto {

	private boolean hasNext;
	
	private boolean hasPrevious;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Long totalElements;
	
	private Integer totalPages;
	
	private List<ArticleDto> articleDtoList;

}

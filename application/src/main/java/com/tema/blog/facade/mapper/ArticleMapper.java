package com.tema.blog.facade.mapper;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tema.blog.entity.Article;
import com.tema.blog.facade.dto.ArticleDto;
import com.tema.blog.facade.dto.ArticlesPageDto;
import com.tema.blog.facade.dto.BasicArticleDto;

@Service
public class ArticleMapper {

	public ArticleDto toDto(Article entity) {
		ArticleDto dto = new ArticleDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setCreationDate(entity.getCreationDate());
		return dto;
	}
	
	
	public Article toEntity(BasicArticleDto dto) {
		Article article = new Article();
		article.setTitle(dto.getTitle());
		article.setContent(dto.getContent());
		article.setCreationDate(new Date());
		return article;
	}
	
	public void mapArticleDtoToArticle(ArticleDto articleDto, Article article) {
		article.setContent(articleDto.getContent());
		article.setTitle(articleDto.getTitle());
	}
	
	public ArticlesPageDto toArticlePageDto(Page<Article> page) {
		ArticlesPageDto pageDto = new ArticlesPageDto();
		pageDto.setHasNext(page.hasNext());
		pageDto.setHasPrevious(page.hasPrevious());
		pageDto.setPageNumber(page.getPageable().getPageNumber());
		pageDto.setPageSize(page.getPageable().getPageSize());
		pageDto.setTotalElements(page.getTotalElements());
		pageDto.setTotalPages(page.getTotalPages());
		List<ArticleDto> articlesDtoList = page.getContent().stream()
				.map(this::toDto)
				.collect(toList());
		pageDto.setArticleDtoList(articlesDtoList);
		return pageDto;
	}
}

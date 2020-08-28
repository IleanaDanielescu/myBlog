package com.tema.blog.facade;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;

import com.tema.blog.facade.dto.ArticleDto;
import com.tema.blog.facade.dto.ArticlesPageDto;
import com.tema.blog.facade.dto.BasicArticleDto;

public interface ArticleFacade {

	void setApplicationEventPublisher(ApplicationEventPublisher publisher);

	Long create(BasicArticleDto basicArticleDto);

	void update(ArticleDto articleDto);

	void delete(Long id);

	List<ArticleDto> searchByTitle(String title);

	ArticlesPageDto getAllPaginated(Integer pageNumber,Integer pageSize);

	List<ArticleDto> getAllArticles();

}
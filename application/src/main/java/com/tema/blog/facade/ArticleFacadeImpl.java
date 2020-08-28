package com.tema.blog.facade;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tema.blog.dto.ArticleDto;
import com.tema.blog.dto.ArticlesPageDto;
import com.tema.blog.dto.BasicArticleDto;
import com.tema.blog.entity.Article;
import com.tema.blog.event.ArticleEvent;
import com.tema.blog.event.ArticleEventType;
import com.tema.blog.exception.BusinessException;
import com.tema.blog.exception.ErrorCode;
import com.tema.blog.facade.config.Facade;
import com.tema.blog.facade.mapper.ArticleMapper;
import com.tema.blog.repository.ArticleRepository;

@Facade
public class ArticleFacadeImpl implements ApplicationEventPublisherAware, ArticleFacade{

	@Autowired
	private ArticleRepository articleRepo;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	private ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public Long create(BasicArticleDto basicArticleDto) {
		checkArticleAlreadyExists(basicArticleDto.getTitle(), null);
		Article article = articleMapper.toEntity(basicArticleDto);
		articleRepo.save(article);
		publisher.publishEvent(new ArticleEvent(this, ArticleEventType.ADD 
				,articleMapper.toDto(article)));
		return article.getId();
	}

	@Override
	public void update(ArticleDto articleDto) {
		Article article = articleRepo.findById(articleDto.getId())
				.orElseThrow(() ->new BusinessException("error.article.not.found", ErrorCode.ARTICLE_NOT_FOUND));
		checkArticleAlreadyExists(articleDto.getTitle(), articleDto.getId());
		articleMapper.mapArticleDtoToArticle(articleDto, article);
		articleRepo.save(article);		
		publisher.publishEvent(new ArticleEvent(this, ArticleEventType.MODIFY
				,articleMapper.toDto(article)));
	}
	
	@Override
	public void delete(Long id) {
		Optional<Article> articleOpt = articleRepo.findById(id);
		articleOpt.ifPresent(articleRepo::delete);
	}
	
	
	private void checkArticleAlreadyExists(String title, Long id) {
		Optional<Article> articleOpt =articleRepo.findByTitle(title);
		if(articleOpt.isPresent() && !articleOpt.get().getId().equals(id)) {
			throw new BusinessException("error.article.title.already.exists", ErrorCode.TITLE_ALREADY_EXISTS);
		}
	}
	
	@Override
	public List<ArticleDto> searchByTitle(String title){
		return articleRepo.findByTitleContaining(title).stream()
				.map(articleMapper::toDto)
				.collect(toList());
	}
	
	@Override
	public ArticlesPageDto getAllPaginated(Integer pageNumber,Integer pageSize){
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Article> page = articleRepo.findAll(pageable);
		return articleMapper.toArticlePageDto(page);
	}
	
	@Override
	public List<ArticleDto> getAllArticles(){
		return articleRepo.findAll().stream()
			.map(articleMapper::toDto)
			.collect(toList());
	}

		
}

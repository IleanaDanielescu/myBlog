package com.tema.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tema.blog.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom{

	List<Article> findAll();
	
	Optional<Article> findByTitle(String title);
		
	Page<Article> findAll(Pageable pageable);
	
	List<Article> findByTitleContaining(String title);
	
	
}

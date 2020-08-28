package com.tema.blog.facade;

import java.util.List;

import com.tema.blog.dto.CommentDto;

public interface CommentFacade {

	List<CommentDto> getCommentsForArticle(Long articleId);

	Long createCommentForArticle(String text, Long articleId);

	void update(String text, Long commentId);

	void delete(Long id);

}
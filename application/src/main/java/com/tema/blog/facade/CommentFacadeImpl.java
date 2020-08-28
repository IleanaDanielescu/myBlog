package com.tema.blog.facade;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tema.blog.dto.CommentDto;
import com.tema.blog.entity.Article;
import com.tema.blog.entity.Comment;
import com.tema.blog.exception.BusinessException;
import com.tema.blog.exception.ErrorCode;
import com.tema.blog.facade.config.Facade;
import com.tema.blog.repository.ArticleRepository;
import com.tema.blog.repository.CommentRepository;

@Facade
public class CommentFacadeImpl implements CommentFacade {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@Override
	public List<CommentDto> getCommentsForArticle(Long articleId){
		return commentRepo.findByArticleId(articleId).stream()
					.map(CommentDto::new)
					.collect(toList());
	}
	
	@Override
	public Long createCommentForArticle(String text, Long articleId) {
		Comment comment = getNewComment(text);
		comment.setArticle(getArticle(articleId));
		
		commentRepo.save(comment);
		return comment.getId();
	}

	private Article getArticle(Long articleId) {
		Optional<Article> articleOpt = articleRepo.findById(articleId);
		articleOpt.orElseThrow(() ->new BusinessException("error.article.not.found", ErrorCode.ARTICLE_NOT_FOUND));
		Article article = articleOpt.get();
		return article;
	}

	private Comment getNewComment(String text) {
		Comment comment = new Comment();
		comment.setText(text);
		comment.setCreationDate(new Date());
		return comment;
	}
	
	@Override
	public void update(String text, Long commentId) {
		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		Comment comment = commentOpt.orElseThrow(() ->new BusinessException("error.comment.not.found", ErrorCode.COMMENT_NOT_FOUND));
		comment.setText(text);
		commentRepo.save(comment);
	}
	
	@Override
	public void delete(Long id) {
		Optional<Comment> commentOpt = commentRepo.findById(id);
		commentOpt.ifPresent(commentRepo::delete);
	}
}

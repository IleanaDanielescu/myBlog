package com.tema.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tema.blog.dto.CommentDto;
import com.tema.blog.dto.SingleStringValue;
import com.tema.blog.facade.CommentFacade;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/comments")
public class CommentController {

	@Autowired
	private CommentFacade commentFacade;
	
	@Operation(summary = "Get article comments")
	@GetMapping("/{articleId}")
	public List<CommentDto> getComments(@PathVariable Long articleId){
		return commentFacade.getCommentsForArticle(articleId);
	}
	
	@Operation(summary = "Add comment to article")
	@PostMapping("/{articleId}")
	@ResponseStatus(HttpStatus.CREATED)
	public SingleStringValue create(@RequestBody SingleStringValue text
				, @PathVariable Long articleId) {
		String textStr = text.getValue().toString();
		String newId = commentFacade.createCommentForArticle(textStr,articleId).toString();
		return new SingleStringValue(newId);
	}
	
	@Operation(summary = "Update comment")
	@PutMapping("/{commentId}")
	public void update(@RequestBody SingleStringValue text
			, @PathVariable Long commentId) {
		String textStr = text.getValue();
		commentFacade.update(textStr, commentId);
	}
	
	@Operation(summary = "Delete comment")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		commentFacade.delete(id);
	}
}

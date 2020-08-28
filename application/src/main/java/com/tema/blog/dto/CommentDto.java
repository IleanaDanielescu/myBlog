package com.tema.blog.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tema.blog.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {

	private Long id;
	
	@NotBlank(message = "Text cannot be empty")
	@Size(max=4000, message = "The maximul text size is 4000 characters")
	private String text;
	
	private Date creationDate;

	public CommentDto(Comment comment) {
		super();
		this.id = comment.getId();
		this.text = comment.getText();
		this.creationDate = comment.getCreationDate();
	}
	
}

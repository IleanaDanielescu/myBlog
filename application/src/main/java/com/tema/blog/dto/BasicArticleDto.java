package com.tema.blog.facade.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BasicArticleDto {
	
	@NotBlank(message = "Title cannot be empty")
	@Size(max = 1000, message = "The maximul title size is 1000 characters")
	private String title;
	
	@NotBlank(message = "Content cannot be empty")
	private String content;
	
}

package com.tema.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tema.blog.dto.ArticleDto;
import com.tema.blog.dto.ArticlesPageDto;
import com.tema.blog.dto.BasicArticleDto;
import com.tema.blog.dto.SingleStringValue;
import com.tema.blog.facade.ArticleFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

	@Autowired
	private ArticleFacade articleFacade;
	
	@Operation(summary = "Get all articles")
	@GetMapping("/")
	public List<ArticleDto> getArticles(){
		return articleFacade.getAllArticles();
	}
	
	@Operation(summary = "Get paginated articles")
	@GetMapping("/getPaginated")
	public ArticlesPageDto getPaginatedArticles(
			@RequestParam Integer pageNumber
			,@RequestParam Integer pageSize){
		return articleFacade.getAllPaginated(pageNumber, pageSize);
	}
	
	@Operation(summary = "Search by title")
	@GetMapping("/searchTitle/{title}")
	public List<ArticleDto> searchByTitle(@PathVariable String title) {
		return articleFacade.searchByTitle(title);
	}
	
	@Operation(summary = "Create article", security = @SecurityRequirement(name = "basicAuth"))
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public SingleStringValue create(@Valid @RequestBody BasicArticleDto createArticleDto) {
		String newId = articleFacade.create(createArticleDto).toString();
		return new SingleStringValue(newId);
	}
	
	@Operation(summary = "Update article", security = @SecurityRequirement(name = "basicAuth"))
	@PutMapping("/")
	public void update(@Valid @RequestBody ArticleDto articleDto) {
		articleFacade.update(articleDto);
	}
	
	@Operation(summary = "Delete article", security = @SecurityRequirement(name = "basicAuth"))
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		articleFacade.delete(id);
	}
}

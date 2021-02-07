package com.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoney.api.model.Category;
import com.algamoney.api.repository.CategoryRespository;

@RestController
@RequestMapping("/category")
public class CategoryResource {

	@Autowired
	private CategoryRespository categoryRespository;

	@GetMapping
	public List<Category> list() {
		return categoryRespository.findAll();
	}

	@PostMapping	
	public ResponseEntity<Category> create(@RequestBody Category input, HttpServletResponse response) {
		Category savedCategory = categoryRespository.save(input);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedCategory.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(savedCategory);
	}
	
	@GetMapping("/{id}")
	public Category findById(@PathVariable Long id) {
		return categoryRespository.findById(id).orElse(null);
	}
}

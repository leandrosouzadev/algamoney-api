package com.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.event.ResourceCreatedEvent;
import com.algamoney.api.model.Category;
import com.algamoney.api.repository.CategoryRespository;

@RestController
@RequestMapping("/category")
public class CategoryResource {

	@Autowired
	private CategoryRespository categoryRespository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Category> list() {
		return categoryRespository.findAll();
	}

	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category input, HttpServletResponse response) {
		Category savedCategory = categoryRespository.save(input);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedCategory.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Optional<Category> category = categoryRespository.findById(id);
		return category.isPresent() ? ResponseEntity.ok(category.get()) : ResponseEntity.notFound().build();
	}
}

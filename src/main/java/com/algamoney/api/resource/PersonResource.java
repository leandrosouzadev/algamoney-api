package com.algamoney.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.event.ResourceCreatedEvent;
import com.algamoney.api.model.Person;
import com.algamoney.api.repository.PersonRepository;
import com.algamoney.api.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonResource {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person savedPerson = personRepository.save(person);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedPerson.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		personRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
		Person savedPerson = personService.update(id, person);
		return ResponseEntity.ok(savedPerson);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateActiveProperty(@PathVariable Long id, @RequestBody Boolean active) {
		personService.updateActiveProperty(id, active);
	}
}

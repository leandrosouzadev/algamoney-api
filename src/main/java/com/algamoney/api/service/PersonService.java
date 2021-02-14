package com.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Person;
import com.algamoney.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person update(Long id, Person person) {
		Person savedPerson = findOrFail(id);
		BeanUtils.copyProperties(person, savedPerson, "id");
		return personRepository.save(savedPerson);		
	}

	public void updateActiveProperty(Long id, Boolean active) {
		Person savedPerson = findOrFail(id);
		savedPerson.setActive(active);
		personRepository.save(savedPerson);
	}
	
	public Person findOrFail(Long id) {		
		return personRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

}

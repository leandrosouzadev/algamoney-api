package com.algamoney.api.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.AccountingEntry;
import com.algamoney.api.model.Person;
import com.algamoney.api.repository.AccountingEntryRepository;
import com.algamoney.api.repository.PersonRepository;
import com.algamoney.api.service.exception.NonexistentOrInactivePersonException;

@Service
public class AccountingEntryService {
	
	@Autowired
	private AccountingEntryRepository accountingEntryRepository;
	
	@Autowired
	private PersonRepository personRepository;

	public AccountingEntry save(@Valid AccountingEntry accountingEntry) {
		Optional<Person> person = personRepository.findById(accountingEntry.getPerson().getId());
		
		if(!person.isPresent() || person.get().isInactive()) {
			throw new NonexistentOrInactivePersonException();
		}
		
		return accountingEntryRepository.save(accountingEntry);
	}
	
	

}

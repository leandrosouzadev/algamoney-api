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
import com.algamoney.api.model.AccountingEntry;
import com.algamoney.api.repository.AccountingEntryRepository;

@RestController
@RequestMapping("/accounting-entries")
public class AccountingEntryResource {

	@Autowired
	private AccountingEntryRepository accountingEntryRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<AccountingEntry> list() {
		return accountingEntryRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountingEntry> findById(@PathVariable Long id) {
		Optional<AccountingEntry> accountingEntry = accountingEntryRepository.findById(id);
		
		return accountingEntry.isPresent() ? ResponseEntity.ok(accountingEntry.get())
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<AccountingEntry> create(@Valid @RequestBody AccountingEntry accountingEntry, HttpServletResponse response) {
		AccountingEntry savedAccountingEntry = accountingEntryRepository.save(accountingEntry);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedAccountingEntry.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountingEntry);		
	}

}

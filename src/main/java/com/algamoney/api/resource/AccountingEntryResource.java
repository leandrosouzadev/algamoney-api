package com.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.event.ResourceCreatedEvent;
import com.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Error;
import com.algamoney.api.model.AccountingEntry;
import com.algamoney.api.repository.AccountingEntryRepository;
import com.algamoney.api.repository.filter.AccountingEntryFilter;
import com.algamoney.api.service.AccountingEntryService;
import com.algamoney.api.service.exception.NonexistentOrInactivePersonException;

@RestController
@RequestMapping("/accounting-entries")
public class AccountingEntryResource {

	@Autowired
	private AccountingEntryRepository accountingEntryRepository;
	
	@Autowired
	private AccountingEntryService accountingEntryService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public Page<AccountingEntry> findByFilter(AccountingEntryFilter accountingEntryFilter, Pageable pageable) {
		return accountingEntryRepository.findByFilter(accountingEntryFilter, pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountingEntry> findById(@PathVariable Long id) {
		Optional<AccountingEntry> accountingEntry = accountingEntryRepository.findById(id);
		
		return accountingEntry.isPresent() ? ResponseEntity.ok(accountingEntry.get())
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<AccountingEntry> create(@Valid @RequestBody AccountingEntry accountingEntry, HttpServletResponse response) {
		AccountingEntry savedAccountingEntry = accountingEntryService.save(accountingEntry);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedAccountingEntry.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountingEntry);		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		accountingEntryRepository.deleteById(id);
	}
	
	@ExceptionHandler({ NonexistentOrInactivePersonException.class })
	public ResponseEntity<Object> handleNonexistentOrInactivePersonException(NonexistentOrInactivePersonException ex) {
		String userMessage = messageSource.getMessage("person.nonexistent-or-inactive", null, LocaleContextHolder.getLocale());
		String developMessage = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(userMessage, developMessage));	
		return ResponseEntity.badRequest().body(errors);
	}
	

}

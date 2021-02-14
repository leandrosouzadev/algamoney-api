package com.algamoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountingEntryFilter {
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startPaymentDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endPaymentDate;

}

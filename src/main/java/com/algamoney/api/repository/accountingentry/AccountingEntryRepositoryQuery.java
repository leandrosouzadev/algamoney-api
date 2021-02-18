package com.algamoney.api.repository.accountingentry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algamoney.api.model.AccountingEntry;
import com.algamoney.api.repository.filter.AccountingEntryFilter;

public interface AccountingEntryRepositoryQuery {
	
	public Page<AccountingEntry> findByFilter(AccountingEntryFilter accountingEntryFilter, Pageable pageable);

}

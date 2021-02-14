package com.algamoney.api.repository.accountingentry;

import java.util.List;

import com.algamoney.api.model.AccountingEntry;
import com.algamoney.api.repository.filter.AccountingEntryFilter;

public interface AccountingEntryRepositoryQuery {
	
	public List<AccountingEntry> findByFilter(AccountingEntryFilter accountingEntryFilter);

}

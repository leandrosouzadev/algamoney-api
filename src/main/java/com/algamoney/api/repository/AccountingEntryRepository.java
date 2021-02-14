package com.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.AccountingEntry;
import com.algamoney.api.repository.accountingentry.AccountingEntryRepositoryQuery;

public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, Long>, AccountingEntryRepositoryQuery {

}

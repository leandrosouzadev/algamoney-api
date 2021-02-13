package com.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.AccountingEntry;

public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, Long>{

}

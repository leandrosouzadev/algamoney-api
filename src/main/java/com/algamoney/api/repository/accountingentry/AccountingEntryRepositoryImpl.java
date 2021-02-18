package com.algamoney.api.repository.accountingentry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.algamoney.api.model.AccountingEntry;
import com.algamoney.api.model.AccountingEntry_;
import com.algamoney.api.repository.filter.AccountingEntryFilter;

public class AccountingEntryRepositoryImpl implements AccountingEntryRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<AccountingEntry> findByFilter(AccountingEntryFilter accountingEntryFilter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccountingEntry> criteriaQuery = criteriaBuilder.createQuery(AccountingEntry.class);
		Root<AccountingEntry> root = criteriaQuery.from(AccountingEntry.class);

		Predicate[] predicates = createRestrictions(accountingEntryFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);

		TypedQuery<AccountingEntry> typedQuery = entityManager.createQuery(criteriaQuery);
		
		addPagingRestrictions(typedQuery, pageable);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(accountingEntryFilter));
	}


	private Predicate[] createRestrictions(AccountingEntryFilter accountingEntryFilter, CriteriaBuilder criteriaBuilder,
			Root<AccountingEntry> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.hasLength(accountingEntryFilter.getDescription())) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(AccountingEntry_.description)),
					"%" + accountingEntryFilter.getDescription().toLowerCase() + "%"));
		}

		if (accountingEntryFilter.getStartPaymentDate() != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(AccountingEntry_.paymentDate),
					accountingEntryFilter.getStartPaymentDate()));
		}

		if (accountingEntryFilter.getEndPaymentDate() != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(AccountingEntry_.paymentDate),
					accountingEntryFilter.getEndPaymentDate()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPagingRestrictions(TypedQuery<AccountingEntry> typedQuery, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int maxResult = pageable.getPageSize();
		int startPosition = currentPage * maxResult;
		
		typedQuery.setFirstResult(startPosition);
		typedQuery.setMaxResults(maxResult);		
	}
	
	private Long total(AccountingEntryFilter accountingEntryFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<AccountingEntry> root = criteriaQuery.from(AccountingEntry.class);
		
		Predicate[] predicates = createRestrictions(accountingEntryFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}
}

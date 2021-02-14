package com.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountingEntry.class)
public abstract class AccountingEntry_ {

	public static volatile SingularAttribute<AccountingEntry, String> note;
	public static volatile SingularAttribute<AccountingEntry, Person> person;
	public static volatile SingularAttribute<AccountingEntry, LocalDate> dueDate;
	public static volatile SingularAttribute<AccountingEntry, String> description;
	public static volatile SingularAttribute<AccountingEntry, Long> id;
	public static volatile SingularAttribute<AccountingEntry, LocalDate> paymentDate;
	public static volatile SingularAttribute<AccountingEntry, Category> category;
	public static volatile SingularAttribute<AccountingEntry, BigDecimal> value;
	public static volatile SingularAttribute<AccountingEntry, AccountingEntryType> accountingEntryType;

	public static final String NOTE = "note";
	public static final String PERSON = "person";
	public static final String DUE_DATE = "dueDate";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String PAYMENT_DATE = "paymentDate";
	public static final String CATEGORY = "category";
	public static final String VALUE = "value";
	public static final String ACCOUNTING_ENTRY_TYPE = "accountingEntryType";

}


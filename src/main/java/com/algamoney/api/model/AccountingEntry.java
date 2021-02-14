package com.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accounting_entry")
public class AccountingEntry {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(length = 50)
	private String description;
	
	@NotNull
	@Column(name = "due_date")
	private LocalDate dueDate;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@NotNull
	@Column(precision = 10, scale = 2)
	private BigDecimal value;
	
	@Column(length = 100)
	private String note;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "accounting_entry_type")
	private AccountingEntryType accountingEntryType;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
			
}

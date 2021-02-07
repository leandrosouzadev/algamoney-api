package com.algamoney.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

	@Size(min = 3, max = 100)
	@Column(length = 100)
	private String address;
	
	@Size(min = 1, max = 10)
	@Column(length = 10)
	private String number;
	
	@Size(max = 50)
	@Column(length = 50)
	private String complement;
	
	@Size(min = 3, max = 100)
	@Column(length = 100)
	private String neighborhood;
	
	@Size(max = 8)
	@Column(length = 8)
	private String zipCode;
	
	@Size(max = 50)
	@Column(length = 50)
	private String city;
	
	@Size(max = 2)
	@Column(length = 2)
	private String state;
}

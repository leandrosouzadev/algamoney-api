package com.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}

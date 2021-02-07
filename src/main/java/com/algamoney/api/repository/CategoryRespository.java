package com.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.Category;

public interface CategoryRespository extends JpaRepository<Category, Long>{

}

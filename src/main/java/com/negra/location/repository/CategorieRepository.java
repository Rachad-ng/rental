package com.negra.location.repository;

import com.negra.location.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Category, Long> {
}
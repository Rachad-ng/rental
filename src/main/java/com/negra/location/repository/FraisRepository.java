package com.negra.location.repository;

import com.negra.location.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraisRepository extends JpaRepository<Cost, Long> {
}
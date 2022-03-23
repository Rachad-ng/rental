package com.negra.location.repository;

import com.negra.location.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Rental, Long> {
}
package com.negra.location.repository;

import com.negra.location.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntretienRepository extends JpaRepository<Maintenance, Long> {
}
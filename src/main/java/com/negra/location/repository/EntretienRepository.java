package com.negra.location.repository;

import com.negra.location.model.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntretienRepository extends JpaRepository<Entretien, Long> {
}
package com.negra.location.repository;

import com.negra.location.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDateTime;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
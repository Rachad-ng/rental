package com.negra.location.repository;

import com.negra.location.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrator, Long> {
}
package com.negra.location.repository;

import com.negra.location.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoitureRepository extends JpaRepository<Car, Long> {

    public Optional<Car> findByRegistrationNumber(String registrationNumber);

}
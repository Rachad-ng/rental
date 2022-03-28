package com.negra.location.repository;

import com.negra.location.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Reservation, Long> {
}
package com.negra.location.repository;

import com.negra.location.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT B.car.id FROM Booking B WHERE B.startDate <= :startDate AND B.backDate >= :startDate GROUP BY B.car.id")
    List<Long> getReservedCarIds(@Param("startDate") LocalDate startDate);

    @Query("SELECT B.car.id FROM Booking B WHERE B.startDate > :backDate OR B.backDate < :startDate GROUP BY B.car.id")
    List<Long> getReservedCarIds(@Param("startDate") LocalDate startDate, @Param("backDate") LocalDate backDate);

}
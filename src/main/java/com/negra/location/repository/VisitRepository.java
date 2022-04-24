package com.negra.location.repository;

import com.negra.location.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT Count(V) FROM Visit V WHERE V.car.id = :idCar")
    int getCarNumberOfVisists(@Param("idCar") Long idCar);

    @Query("SELECT MAX(V.dateTime) FROM Visit V WHERE V.car.id = :idCar")
    LocalDateTime getCarLastVisistDate(@Param("idCar") Long idCar);
}
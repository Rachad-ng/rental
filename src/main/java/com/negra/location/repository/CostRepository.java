package com.negra.location.repository;

import com.negra.location.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CostRepository extends JpaRepository<Cost, Long> {

    @Query("SELECT C FROM Cost C WHERE C.car.id = :idCar")
    List<Cost> findByCarId(@Param("idCar") Long idCar);

}
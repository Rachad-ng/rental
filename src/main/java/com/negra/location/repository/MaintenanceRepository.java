package com.negra.location.repository;

import com.negra.location.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query("SELECT M FROM Maintenance M WHERE M.car.id = :idCar")
    List<Maintenance> findByCarId(@Param("idCar") Long idCar);

}
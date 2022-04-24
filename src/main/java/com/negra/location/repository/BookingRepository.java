package com.negra.location.repository;

import com.negra.location.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Gestion des contraints de disponibilié des voitures start
    @Query("SELECT B.car.id FROM Booking B WHERE B.startDate <= :startDate AND B.backDate >= :startDate GROUP BY B.car.id")
    List<Long> getNotReservedCarIds(@Param("startDate") LocalDate startDate);

    // Gestion des contraints de disponibilié des voitures start
    @Query("SELECT B.car.id FROM Booking B WHERE B.startDate > :backDate OR B.backDate < :startDate GROUP BY B.car.id")
    List<Long> getNotReservedCarIds(@Param("startDate") LocalDate startDate, @Param("backDate") LocalDate backDate);

    @Query("SELECT B FROM Booking B WHERE B.user.id = :idUser ORDER BY B.id DESC")
    Page<Booking> getClientBookings(@Param("idUser") Long idUser, Pageable pageable);

    @Query("SELECT B FROM Booking B WHERE B.car.agent.id = :idUser ORDER BY B.id DESC")
    Page<Booking> getAgentBookings(@Param("idUser") Long idUser, Pageable pageable);

    @Modifying
    @Query("UPDATE Booking B SET B.state = 'confirmed' WHERE B.id = :id")
    void confirmBooking(@Param("id") Long id);

    @Query("SELECT Count(B) FROM Booking B WHERE B.car.id = :idCar AND B.state = 'inprogress'")
    int getCarNumberOfBookingsInProgress(@Param("idCar") Long idCar);
}
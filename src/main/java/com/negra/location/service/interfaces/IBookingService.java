package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Client;
import com.negra.location.model.Booking;
import com.negra.location.model.Car;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {

    void createReservation(Booking booking, Client client, Car car) throws DataStoreException;
    void deleteReservation(Booking booking) throws DataStoreException;
    List<Long> getReservedCarIds(LocalDate startDate);
    List<Long> getReservedCarIds(LocalDate startDate, LocalDate backDate);
}

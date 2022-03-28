package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Client;
import com.negra.location.model.Reservation;
import com.negra.location.model.Car;

public interface IBookingService {

    void createReservation(Reservation reservation, Client client, Car car) throws DataStoreException;
    void deleteReservation(Reservation reservation) throws DataStoreException;

}

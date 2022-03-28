package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Rental;
import com.negra.location.model.Reduction;
import com.negra.location.model.Reservation;

public interface IRentalService {

    void createLocation(Rental rental, Reservation reservation, Reduction reduction) throws DataStoreException;
    void deleteLocation(Rental rental) throws DataStoreException;
}

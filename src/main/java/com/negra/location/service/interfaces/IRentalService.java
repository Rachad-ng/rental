package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Rental;
import com.negra.location.model.Reduction;
import com.negra.location.model.Booking;

public interface IRentalService {

    void createLocation(Rental rental, Booking booking, Reduction reduction) throws DataStoreException;
    void deleteLocation(Rental rental) throws DataStoreException;
}

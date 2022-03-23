package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Rental;
import com.negra.location.model.Reduction;
import com.negra.location.model.Reservation;
import com.negra.location.repository.LocationRepository;
import com.negra.location.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void createLocation(Rental rental, Reservation reservation, Reduction reduction){
        reservation.addRental(rental);
        reduction.addLocation(rental);
        try {
            locationRepository.save(rental);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }

    }

    public void deleteLocation(Rental rental){
        rental.getReservation().removeRental();
        rental.getReduction().removeLocation(rental);
        try {
            locationRepository.delete(rental);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}

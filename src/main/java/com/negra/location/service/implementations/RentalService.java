package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Booking;
import com.negra.location.model.Rental;
import com.negra.location.model.Reduction;
import com.negra.location.repository.LocationRepository;
import com.negra.location.service.interfaces.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class RentalService implements IRentalService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void createLocation(Rental rental, Booking booking, Reduction reduction){
        booking.addRental(rental);
        reduction.addLocation(rental);
        try {
            locationRepository.save(rental);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }

    }

    @Override
    public void deleteLocation(Rental rental){
        rental.getBooking().removeRental();
        rental.getReduction().removeLocation(rental);
        try {
            locationRepository.delete(rental);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}

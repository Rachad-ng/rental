package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Client;
import com.negra.location.model.Rental;
import com.negra.location.model.Reservation;
import com.negra.location.model.Car;
import com.negra.location.repository.BookingRepository;
import com.negra.location.service.interfaces.IRentalService;
import com.negra.location.service.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private IRentalService locationService;

    public void createReservation(Reservation reservation, Client client, Car car){
        client.addReservation(reservation);
        car.addReservation(reservation);

        try{
            bookingRepository.save(reservation);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }

    }

    public void deleteReservation(Reservation reservation){
        reservation.getClient().removeReservation(reservation);
        reservation.getCar().removeReservation(reservation);

        Rental rental = reservation.getRental();

        try{
            if(rental != null)
                locationService.deleteLocation(rental);

            bookingRepository.delete(reservation);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}

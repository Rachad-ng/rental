package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Booking;
import com.negra.location.model.Client;
import com.negra.location.model.Rental;
import com.negra.location.model.Car;
import com.negra.location.repository.BookingRepository;
import com.negra.location.service.interfaces.IRentalService;
import com.negra.location.service.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private IRentalService locationService;

    @Override
    public void createReservation(Booking booking, Client client, Car car){
        client.addReservation(booking);
        car.addReservation(booking);

        try{
            bookingRepository.save(booking);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }

    }

    @Override
    public void deleteReservation(Booking booking){
        booking.getClient().removeReservation(booking);
        booking.getCar().removeReservation(booking);

        Rental rental = booking.getRental();

        try{
            if(rental != null)
                locationService.deleteLocation(rental);

            bookingRepository.delete(booking);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public List<Long> getReservedCarIds(LocalDate startDate) {
        return bookingRepository.getReservedCarIds(startDate);
    }

    @Override
    public List<Long> getReservedCarIds(LocalDate startDate, LocalDate backDate) {
        return bookingRepository.getReservedCarIds(startDate, backDate);
    }

}

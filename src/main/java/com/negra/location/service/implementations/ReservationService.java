package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Client;
import com.negra.location.model.Rental;
import com.negra.location.model.Reservation;
import com.negra.location.model.Car;
import com.negra.location.repository.ReservationRepository;
import com.negra.location.service.interfaces.ILocationService;
import com.negra.location.service.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class ReservationService implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ILocationService locationService;

    public void createReservation(Reservation reservation, Client client, Car car){
        client.addReservation(reservation);
        car.addReservation(reservation);

        try{
            reservationRepository.save(reservation);
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

            reservationRepository.delete(reservation);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}

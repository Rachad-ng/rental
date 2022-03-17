package com.negra.location.service;

import com.negra.location.model.Client;
import com.negra.location.model.Location;
import com.negra.location.model.Reservation;
import com.negra.location.model.Voiture;
import com.negra.location.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LocationService locationService;

    public void createReservation(Reservation reservation, Client client, Voiture voiture){
        client.addReservation(reservation);
        voiture.addReservation(reservation);
        reservationRepository.save(reservation);
    }

    public void deleteReservation(Reservation reservation){
        reservation.getClient().removeReservation(reservation);
        reservation.getVoiture().removeReservation(reservation);

        Location location = reservation.getLocation();
        if(location != null)
            locationService.deleteLocation(location);

        reservationRepository.delete(reservation);
    }

}

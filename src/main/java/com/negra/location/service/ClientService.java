package com.negra.location.service;

import com.negra.location.entity.Client;
import com.negra.location.entity.Reservation;
import com.negra.location.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationService reservationService;

    public void createClient(Client client){
        clientRepository.save(client);
    }

    // Supprimer le client après la suppression des reservations associes
    public void deleteClient(Client client){
        Set<Reservation> reservations = client.getReservations();
        for (Reservation reservation: reservations)
            reservationService.deleteReservation(reservation);

        clientRepository.delete(client);
    }

}

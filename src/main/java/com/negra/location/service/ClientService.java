package com.negra.location.service;

import com.negra.location.dto.ClientRegistrationDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.model.Client;
import com.negra.location.model.Reservation;
import com.negra.location.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createClient(ClientRegistrationDto clientRegistrationDto) throws AlreadyExistsException {
        utilisateurService.isUserExists(clientRegistrationDto.getEmail());
        Client client = new Client();
        mapperClientRegitrationDtoToClient(clientRegistrationDto, client);
        clientRepository.save(client);
    }

    // Supprimer le client après la suppression des reservations associes
    public void deleteClient(Client client){
        Set<Reservation> reservations = client.getReservations();
        for (Reservation reservation: reservations)
            reservationService.deleteReservation(reservation);

        clientRepository.delete(client);
    }

    private void mapperClientRegitrationDtoToClient(ClientRegistrationDto clientRegistrationDto, Client client){
        client.setNom(clientRegistrationDto.getNom());
        client.setPrenom(clientRegistrationDto.getPrenom());
        client.setEmail(clientRegistrationDto.getEmail());
        client.setPassword(passwordEncoder.encode(clientRegistrationDto.getPassword()));
        client.setTel(clientRegistrationDto.getTel());
        client.setRole(clientRegistrationDto.getRole());
    }

}

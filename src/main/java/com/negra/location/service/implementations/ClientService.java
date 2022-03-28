package com.negra.location.service.implementations;

import com.negra.location.dto.ClientRegistrationDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Client;
import com.negra.location.model.Reservation;
import com.negra.location.repository.ClientRepository;
import com.negra.location.service.interfaces.IClientService;
import com.negra.location.service.interfaces.IBookingService;
import com.negra.location.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private IUserService utilisateurService;
    @Autowired
    private IBookingService reservationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createClient(ClientRegistrationDto clientRegistrationDto) throws AlreadyExistsException, DataStoreException {
        utilisateurService.isUserExists(clientRegistrationDto.getEmail());
        Client client = new Client();
        MapperService.clientRegitrationDtoToClient(clientRegistrationDto, client);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        try {
            clientRepository.save(client);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    // Supprimer le client après la suppression des reservations associes
    public void deleteClient(Client client) {
        Set<Reservation> reservations = client.getReservationSet();
        for (Reservation reservation: reservations)
            reservationService.deleteReservation(reservation);

        try {
            clientRepository.delete(client);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

}

package com.negra.location.service.interfaces;

import com.negra.location.dto.ClientRegistrationDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Client;

public interface IClientService {

    void createClient(ClientRegistrationDto clientRegistrationDto) throws AlreadyExistsException;
    void deleteClient(Client client) throws DataStoreException;
}

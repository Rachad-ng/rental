package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Administrator;

public interface IAdministratorService {

    void createAdministrateur(Administrator administrator) throws DataStoreException;
}

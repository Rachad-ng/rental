package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Address;
import com.negra.location.model.Agent;

public interface IAddressService {

    void createAddress(Address address, Agent agent) throws DataStoreException;
    void deleteAddress(Address address) throws DataStoreException;

}

package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Address;
import com.negra.location.model.Agent;
import com.negra.location.repository.AddressRepository;
import com.negra.location.service.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void createAddress(Address address, Agent agent){
        agent.addAddress(address);
        try {
            addressRepository.save(address);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    @Override
    public void deleteAddress(Address address){
        address.getAgent().removeAdresse(address);
        try {
            addressRepository.delete(address);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public List<String> findAvailableTowns() {
        return addressRepository.findAvailableTowns();
    }

}
